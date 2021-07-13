package latibro.automation.base.world.reference.card

import groovy.transform.CompileStatic
import latibro.automation.AutomationMod
import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.gui.GuiTextField
import net.minecraft.client.resources.I18n
import org.lwjgl.input.Keyboard

@CompileStatic
class WorldReferenceSourceCardScreen extends GuiScreen {

    private final WorldReferenceSourceCardContainer container

    private GuiButton doneButton
    private GuiButton cancelButton

    private GuiTextField worldNameField

    WorldReferenceSourceCardScreen(WorldReferenceSourceCardContainer container) {
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
    }

    @Override
    void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground()
        String name = I18n.format(WorldReferenceSourceCardItem.INSTANCE.getUnlocalizedName())
        drawCenteredString(fontRenderer, name, width / 2 as int, 20, 16777215)
        drawString(fontRenderer, I18n.format(WorldReferenceSourceCardItem.INSTANCE.getUnlocalizedName() + ".reference.name"), width / 2 - 150 as int, 40, 10526880)
        worldNameField?.drawTextBox()
        super.drawScreen(mouseX, mouseY, partialTicks)
    }

    @Override
    void updateScreen() {
        if (worldNameField.isFocused()) {
            worldNameField?.updateCursorCounter()
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
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode)

        if (worldNameField.isFocused()) {
            worldNameField.textboxKeyTyped(typedChar, keyCode)
        }
    }

}
