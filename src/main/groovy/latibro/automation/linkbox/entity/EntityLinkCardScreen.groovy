package latibro.automation.linkbox.entity

import groovy.transform.CompileStatic
import latibro.automation.AutomationMod
import latibro.automation.ModItems
import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.gui.GuiTextField
import net.minecraft.client.resources.I18n
import org.lwjgl.input.Keyboard

@CompileStatic
class EntityLinkCardScreen extends GuiScreen {

    private final EntityLinkCardContainer container

    private GuiButton doneButton
    private GuiButton cancelButton

    private GuiTextField entityUuidField

    EntityLinkCardScreen(EntityLinkCardContainer container) {
        this.container = Objects.requireNonNull(container)
    }

    @Override
    void initGui() {
        Keyboard.enableRepeatEvents(true)
        buttonList.clear()

        doneButton = addButton(new GuiButton(0, width / 2 - 4 - 150 as int, height / 4 + 120 + 12 as int, 150, 20, I18n.format("gui.done")))

        cancelButton = addButton(new GuiButton(1, width / 2 + 4 as int, height / 4 + 120 + 12 as int, 150, 20, I18n.format("gui.cancel")))

        entityUuidField = new GuiTextField(2, fontRenderer, width / 2 - 150 as int, 50, 300, 20)
        entityUuidField.setMaxStringLength(50)
        entityUuidField.setFocused(true)
        entityUuidField.setText(container.getEntityUUID()?.toString()?: "")
    }

    @Override
    void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground()
        String name = I18n.format(ModItems.locationLinkCard.getUnlocalizedName() + ".name")
        drawCenteredString(fontRenderer, name, width / 2 as int, 20, 16777215)
        drawString(fontRenderer, I18n.format(ModItems.locationLinkCard.getUnlocalizedName() + ".entity.uuid.name"), width / 2 - 150 as int, 40, 10526880)
        entityUuidField?.drawTextBox()
        super.drawScreen(mouseX, mouseY, partialTicks)
    }

    @Override
    void updateScreen() {
        if (entityUuidField.isFocused()) {
            entityUuidField?.updateCursorCounter()
        }
    }

    @Override
    void onGuiClosed() {
        Keyboard.enableRepeatEvents(false)
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.enabled) {
            if (button == doneButton) {
                if (entityUuidField.getText()) {
                    try {
                        def entityUuid = UUID.fromString(entityUuidField.getText())
                        container.setEntityUUID(entityUuid)
                        mc.displayGuiScreen((GuiScreen) null)
                    } catch (IllegalArgumentException e) {
                        AutomationMod.logger.warn(e)
                    }
                }
                return
            }
            if (button == cancelButton) {
                mc.displayGuiScreen((GuiScreen) null)
                return
            }
            AutomationMod.logger.debug("Unknown button {}", button)
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton)

        entityUuidField.mouseClicked(mouseX, mouseY, mouseButton)
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode)

        if (entityUuidField.isFocused()) {
            entityUuidField.textboxKeyTyped(typedChar, keyCode)
        }
    }

}
