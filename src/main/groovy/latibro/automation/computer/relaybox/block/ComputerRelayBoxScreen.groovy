package latibro.automation.computer.relaybox.block

import groovy.transform.CompileStatic
import latibro.automation.AutomationMod
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.resources.I18n
import net.minecraft.util.ResourceLocation

@CompileStatic
class ComputerRelayBoxScreen extends GuiContainer {

    private static final ResourceLocation BACKGROUD_TEXTURE = new ResourceLocation(AutomationMod.MODID, "textures/gui/computer-relay-box.png")

    ComputerRelayBoxScreen(ComputerRelayBoxContainer container) {
        super(container)
    }

    @Override
    void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground()
        super.drawScreen(mouseX, mouseY, partialTicks)
        renderHoveredToolTip(mouseX, mouseY)
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1, 1, 1, 1)
        mc.getTextureManager().bindTexture(BACKGROUD_TEXTURE)
        int x = (width - xSize) / 2 as int
        int y = (height - ySize) / 2 as int
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize)
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String name = I18n.format(ComputerRelayBoxBlock.INSTANCE.getUnlocalizedName())
        fontRenderer.drawString(name, 8, 6, 0x404040)
        fontRenderer.drawString(I18n.format(ComputerRelayBoxBlock.INSTANCE.getUnlocalizedName() + ".player-inventory"), 8, ySize - 94, 0x404040)
    }

}
