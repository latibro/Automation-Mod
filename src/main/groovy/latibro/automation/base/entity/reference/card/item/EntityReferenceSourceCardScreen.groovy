package latibro.automation.base.entity.reference.card.item

import groovy.transform.CompileStatic
import latibro.automation.AutomationMod
import latibro.automation.base.entity.reference.card.item.EntityReferenceSourceCardContainer
import latibro.automation.base.entity.reference.card.item.EntityReferenceSourceCardItem
import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.gui.GuiTextField
import net.minecraft.client.resources.I18n
import org.lwjgl.input.Keyboard

@CompileStatic
class EntityReferenceSourceCardScreen extends GuiScreen {

    private final EntityReferenceSourceCardContainer container

    private GuiButton doneButton
    private GuiButton cancelButton

    private GuiTextField entityUuidField

    EntityReferenceSourceCardScreen(EntityReferenceSourceCardContainer container) {
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
        entityUuidField.setText(container.entityUuid?: "")
    }

    @Override
    void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground()
        String name = I18n.format(EntityReferenceSourceCardItem.INSTANCE.getUnlocalizedName())
        drawCenteredString(fontRenderer, name, width / 2 as int, 20, 16777215)
        drawString(fontRenderer, I18n.format(EntityReferenceSourceCardItem.INSTANCE.getUnlocalizedName() + ".reference.uuid"), width / 2 - 150 as int, 40, 10526880)
        entityUuidField?.drawTextBox()
        super.drawScreen(mouseX, mouseY, partialTicks)
    }

    @Override
    void updateScreen() {
        if (entityUuidField.isFocused()) {
            entityUuidField?.updateCursorCounter()
        }
    }

    void resetData() {
        entityUuidField.setText(container.getEntityUuid())
    }

    void saveData() {
        //TODO validate data
        container.setEntityUuid(entityUuidField.getText())

        container.detectAndSendChanges()
    }

    void closeScreen() {
        mc.displayGuiScreen((GuiScreen) null)
    }

    @Override
    void onGuiClosed() {
        Keyboard.enableRepeatEvents(false)
        //TODO save data on close
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.enabled) {
            if (button == doneButton) {
                saveData()
                closeScreen()
                return
            }
            if (button == cancelButton) {
                resetData()
                closeScreen()
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
