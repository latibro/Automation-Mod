package latibro.automation.linkbox.data

import groovy.transform.CompileStatic
import latibro.automation.AutomationMod
import latibro.automation.ModBlocks
import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.gui.GuiTextField
import net.minecraft.client.resources.I18n
import org.lwjgl.input.Keyboard

@CompileStatic
class DataBoxScreen extends GuiScreen {

    private final DataBoxContainer container

    private GuiButton doneButton
    private GuiButton cancelButton
    private GuiTextField dataField

    DataBoxScreen(DataBoxContainer container) {
        this.container = Objects.requireNonNull(container)
    }

    @Override
    void initGui() {
        Keyboard.enableRepeatEvents(true)
        buttonList.clear()

        doneButton = addButton(new GuiButton(0, width / 2 - 4 - 150 as int, height / 4 + 120 + 12 as int, 150, 20, I18n.format("gui.done")))

        cancelButton = addButton(new GuiButton(1, width / 2 + 4 as int, height / 4 + 120 + 12 as int, 150, 20, I18n.format("gui.cancel")))

        dataField = new GuiTextField(2, fontRenderer, this.width / 2 - 150 as int, 50, 300, 20)
        dataField.setMaxStringLength(4096)
        dataField.setFocused(true)
        dataField.setText(container.getData())
    }

    @Override
    void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground()
        drawCenteredString(fontRenderer, ModBlocks.dataBox.getLocalizedName(), width / 2 as int, 20, 16777215)
        drawString(fontRenderer, I18n.format(ModBlocks.dataBox.getUnlocalizedName() + ".data"), width / 2 - 150 as int, 40, 10526880)
        dataField?.drawTextBox()
        super.drawScreen(mouseX, mouseY, partialTicks)
    }

    @Override
    void updateScreen() {
        dataField?.updateCursorCounter()
    }

    @Override
    void onGuiClosed() {
        Keyboard.enableRepeatEvents(false)
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.enabled) {
            if (button == doneButton) {
                container.setData(dataField.getText())
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

        dataField.mouseClicked(mouseX, mouseY, mouseButton)
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode)

        if (dataField.isFocused()) {
            dataField.textboxKeyTyped(typedChar, keyCode)
        }
    }

}
