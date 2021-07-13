package latibro.automation.linkbox.redstone

import groovy.transform.CompileStatic
import latibro.automation.AutomationMod
import latibro.automation.ModBlocks
import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.gui.GuiTextField
import net.minecraft.client.resources.I18n
import org.lwjgl.input.Keyboard

@CompileStatic
class RedstoneBoxScreen extends GuiScreen {

    private final RedstoneBoxContainer container

    private GuiButton doneButton
    private GuiButton cancelButton
    private GuiTextField powerLevelField

    RedstoneBoxScreen(RedstoneBoxContainer container) {
        this.container = Objects.requireNonNull(container)
    }

    @Override
    void initGui() {
        Keyboard.enableRepeatEvents(true)
        buttonList.clear()

        doneButton = addButton(new GuiButton(0, width / 2 - 4 - 150 as int, height / 4 + 120 + 12 as int, 150, 20, I18n.format("gui.done")))

        cancelButton = addButton(new GuiButton(1, width / 2 + 4 as int, height / 4 + 120 + 12 as int, 150, 20, I18n.format("gui.cancel")))

        powerLevelField = new GuiTextField(2, fontRenderer, this.width / 2 - 150 as int, 50, 300, 20)
        powerLevelField.setMaxStringLength(32500)
        powerLevelField.setFocused(true)
        powerLevelField.setValidator { text ->
            if (!text) {
                return true
            }
            if (text?.isInteger()) {
                return (0..15).contains(text.toInteger())
            }
            return false
        }
        powerLevelField.setText(container.getPowerLevel() as String)
    }

    @Override
    void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground()
        String name = I18n.format(ModBlocks.redstoneBox.getUnlocalizedName())
        drawCenteredString(fontRenderer, name, width / 2 as int, 20, 16777215)
        drawString(fontRenderer, I18n.format(ModBlocks.redstoneBox.getUnlocalizedName() + ".power-level"), width / 2 - 150 as int, 40, 10526880)
        powerLevelField?.drawTextBox()
        super.drawScreen(mouseX, mouseY, partialTicks)
    }

    @Override
    void updateScreen() {
        powerLevelField?.updateCursorCounter()
    }

    @Override
    void onGuiClosed() {
        Keyboard.enableRepeatEvents(false)
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.enabled) {
            if (button == doneButton) {
                if (powerLevelField.getText()?.isInteger()) {
                    container.setPowerLevel(powerLevelField.getText().toInteger())
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

        powerLevelField.mouseClicked(mouseX, mouseY, mouseButton)
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode)

        if (powerLevelField.isFocused()) {
            powerLevelField.textboxKeyTyped(typedChar, keyCode)
        }
    }

}
