package latibro.automation.linkbox.location

import groovy.transform.CompileStatic
import latibro.automation.AutomationMod
import latibro.automation.ModItems
import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.gui.GuiTextField
import net.minecraft.client.resources.I18n
import net.minecraft.util.math.BlockPos
import org.lwjgl.input.Keyboard

@CompileStatic
class LocationLinkCardScreen extends GuiScreen {

    private final LocationLinkCardContainer container

    private GuiButton doneButton
    private GuiButton cancelButton

    private GuiTextField xCoordinateField
    private GuiTextField yCoordinateField
    private GuiTextField zCoordinateField

    LocationLinkCardScreen(LocationLinkCardContainer container) {
        this.container = Objects.requireNonNull(container)
    }

    @Override
    void initGui() {
        Keyboard.enableRepeatEvents(true)
        buttonList.clear()

        doneButton = addButton(new GuiButton(0, width / 2 - 4 - 150 as int, height / 4 + 120 + 12 as int, 150, 20, I18n.format("gui.done")))

        cancelButton = addButton(new GuiButton(1, width / 2 + 4 as int, height / 4 + 120 + 12 as int, 150, 20, I18n.format("gui.cancel")))

        xCoordinateField = new GuiTextField(2, fontRenderer, width / 2 - 150 as int, 50, 100, 20)
        xCoordinateField.setMaxStringLength(10)
        xCoordinateField.setValidator(validator)
        xCoordinateField.setFocused(true)
        xCoordinateField.setText(container.getLocation()?.getX() as String?: "")

        yCoordinateField = new GuiTextField(3, fontRenderer, width / 2 - 150 as int, 90, 100, 20)
        yCoordinateField.setMaxStringLength(10)
        xCoordinateField.setValidator(validator)
        yCoordinateField.setText(container.getLocation()?.getY() as String?: "")

        zCoordinateField = new GuiTextField(4, fontRenderer, width / 2 - 150 as int, 130, 100, 20)
        zCoordinateField.setMaxStringLength(10)
        xCoordinateField.setValidator(validator)
        zCoordinateField.setText(container.getLocation()?.getZ() as String?: "")
    }

    @Override
    void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground()
        String name = I18n.format(ModItems.locationLinkCard.getUnlocalizedName() + ".name")
        drawCenteredString(fontRenderer, name, width / 2 as int, 20, 16777215)
        drawString(fontRenderer, I18n.format(ModItems.locationLinkCard.getUnlocalizedName() + ".location.x.name"), width / 2 - 150 as int, 40, 10526880)
        xCoordinateField?.drawTextBox()
        drawString(fontRenderer, I18n.format(ModItems.locationLinkCard.getUnlocalizedName() + ".location.y.name"), width / 2 - 150 as int, 80, 10526880)
        yCoordinateField?.drawTextBox()
        drawString(fontRenderer, I18n.format(ModItems.locationLinkCard.getUnlocalizedName() + ".location.z.name"), width / 2 - 150 as int, 120, 10526880)
        zCoordinateField?.drawTextBox()
        super.drawScreen(mouseX, mouseY, partialTicks)
    }

    @Override
    void updateScreen() {
        if (xCoordinateField.isFocused()) {
            xCoordinateField?.updateCursorCounter()
        }
        if (yCoordinateField.isFocused()) {
            yCoordinateField?.updateCursorCounter()
        }
        if (zCoordinateField.isFocused()) {
            zCoordinateField?.updateCursorCounter()
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
                if (xCoordinateField.getText()?.isInteger() && yCoordinateField.getText()?.isInteger() && zCoordinateField.getText()?.isInteger()) {
                    def location = new BlockPos(xCoordinateField.getText().toInteger(), yCoordinateField.getText().toInteger(), zCoordinateField.getText().toInteger())
                    container.setLocation(location)
                    mc.displayGuiScreen((GuiScreen) null)
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

        xCoordinateField.mouseClicked(mouseX, mouseY, mouseButton)
        yCoordinateField.mouseClicked(mouseX, mouseY, mouseButton)
        zCoordinateField.mouseClicked(mouseX, mouseY, mouseButton)
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode)

        if (xCoordinateField.isFocused()) {
            xCoordinateField.textboxKeyTyped(typedChar, keyCode)
        }
        if (yCoordinateField.isFocused()) {
            yCoordinateField.textboxKeyTyped(typedChar, keyCode)
        }
        if (zCoordinateField.isFocused()) {
            zCoordinateField.textboxKeyTyped(typedChar, keyCode)
        }
    }

    private static Closure validator = { String text ->
        if (!text) {
            return true
        }
        if (text == "-") {
            return true
        }
        if (text?.isInteger()) {
            return true
        }
        return false
    }

}
