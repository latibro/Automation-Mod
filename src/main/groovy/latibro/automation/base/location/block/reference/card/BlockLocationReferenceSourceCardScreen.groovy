package latibro.automation.base.location.block.reference.card

import groovy.transform.CompileStatic
import latibro.automation.AutomationMod
import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.gui.GuiTextField
import net.minecraft.client.resources.I18n
import org.lwjgl.input.Keyboard

@CompileStatic
class BlockLocationReferenceSourceCardScreen extends GuiScreen {

    private final BlockLocationReferenceSourceCardContainer container

    private GuiButton doneButton
    private GuiButton cancelButton

    private GuiTextField worldNameField
    private GuiTextField coordinateXField
    private GuiTextField coordinateYField
    private GuiTextField coordinateZField

    BlockLocationReferenceSourceCardScreen(BlockLocationReferenceSourceCardContainer container) {
        this.container = Objects.requireNonNull(container)
    }

    @Override
    void initGui() {
        Keyboard.enableRepeatEvents(true)
        buttonList.clear()

        doneButton = addButton(new GuiButton(0, width / 2 - 4 - 150 as int, height / 4 + 120 + 12 as int, 150, 20, I18n.format("gui.done")))

        cancelButton = addButton(new GuiButton(1, width / 2 + 4 as int, height / 4 + 120 + 12 as int, 150, 20, I18n.format("gui.cancel")))

        worldNameField = new GuiTextField(2, fontRenderer, width / 2 - 150 as int, 50, 100, 20)
        worldNameField.setFocused(true)
        worldNameField.setText(container.worldName?: "")

        coordinateXField = new GuiTextField(3, fontRenderer, width / 2 - 150 as int, 90, 100, 20)
        coordinateXField.setMaxStringLength(10)
        coordinateXField.setValidator(integerValidator)
        coordinateXField.setText(container.coordinateX?: "")

        coordinateYField = new GuiTextField(4, fontRenderer, width / 2 - 150 as int, 130, 100, 20)
        coordinateYField.setMaxStringLength(10)
        coordinateYField.setValidator(integerValidator)
        coordinateYField.setText(container.coordinateY?: "")

        coordinateZField = new GuiTextField(5, fontRenderer, width / 2 - 150 as int, 170, 100, 20)
        coordinateZField.setMaxStringLength(10)
        coordinateZField.setValidator(integerValidator)
        coordinateZField.setText(container.coordinateZ?: "")
    }

    @Override
    void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground()
        String name = I18n.format(BlockLocationReferenceSourceCardItem.INSTANCE.getUnlocalizedName())
        drawCenteredString(fontRenderer, name, width / 2 as int, 20, 16777215)
        drawString(fontRenderer, I18n.format(BlockLocationReferenceSourceCardItem.INSTANCE.getUnlocalizedName() + ".reference.world.name"), width / 2 - 150 as int, 40, 10526880)
        worldNameField?.drawTextBox()
        drawString(fontRenderer, I18n.format(BlockLocationReferenceSourceCardItem.INSTANCE.getUnlocalizedName() + ".reference.coordinates.x"), width / 2 - 150 as int, 80, 10526880)
        coordinateYField?.drawTextBox()
        drawString(fontRenderer, I18n.format(BlockLocationReferenceSourceCardItem.INSTANCE.getUnlocalizedName() + ".reference.coordinates.y"), width / 2 - 150 as int, 120, 10526880)
        coordinateZField?.drawTextBox()
        drawString(fontRenderer, I18n.format(BlockLocationReferenceSourceCardItem.INSTANCE.getUnlocalizedName() + ".reference.coordinates.z"), width / 2 - 150 as int, 160, 10526880)
        coordinateZField?.drawTextBox()
        super.drawScreen(mouseX, mouseY, partialTicks)
    }

    @Override
    void updateScreen() {
        if (worldNameField.isFocused()) {
            worldNameField?.updateCursorCounter()
        }
        if (coordinateXField.isFocused()) {
            coordinateXField?.updateCursorCounter()
        }
        if (coordinateYField.isFocused()) {
            coordinateYField?.updateCursorCounter()
        }
        if (coordinateZField.isFocused()) {
            coordinateZField?.updateCursorCounter()
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
                container.setWorldName(worldNameField.getText())
                container.setCoordinateX(parseInteger(coordinateXField.getText()))
                container.setCoordinateY(parseInteger(coordinateYField.getText()))
                container.setCoordinateZ(parseInteger(coordinateZField.getText()))

                container.detectAndSendChanges()

                mc.displayGuiScreen((GuiScreen) null)
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

        worldNameField.mouseClicked(mouseX, mouseY, mouseButton)
        coordinateXField.mouseClicked(mouseX, mouseY, mouseButton)
        coordinateYField.mouseClicked(mouseX, mouseY, mouseButton)
        coordinateZField.mouseClicked(mouseX, mouseY, mouseButton)
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode)

        if (worldNameField.isFocused()) {
            worldNameField.textboxKeyTyped(typedChar, keyCode)
        }
        if (coordinateXField.isFocused()) {
            coordinateXField.textboxKeyTyped(typedChar, keyCode)
        }
        if (coordinateYField.isFocused()) {
            coordinateYField.textboxKeyTyped(typedChar, keyCode)
        }
        if (coordinateZField.isFocused()) {
            coordinateZField.textboxKeyTyped(typedChar, keyCode)
        }
    }

    private static Integer parseInteger(String text) {
        if (text?.isInteger()) {
            return Integer.parseInt(text)
        } else {
            null
        }
    }

    private static Closure integerValidator = { String text ->
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
