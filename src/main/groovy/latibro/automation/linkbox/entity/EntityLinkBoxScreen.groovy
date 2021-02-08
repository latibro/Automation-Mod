package latibro.automation.linkbox.entity

import groovy.transform.CompileStatic
import latibro.automation.AutomationMod
import latibro.automation.ModBlocks
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.util.ResourceLocation

@CompileStatic
class EntityLinkBoxScreen extends GuiContainer {

    private static final ResourceLocation BACKGROUD_TEXTURE = new ResourceLocation(AutomationMod.MODID, "textures/gui/entity_link_box.png")

    private final InventoryPlayer playerInventory

    EntityLinkBoxScreen(EntityLinkBoxContainer container, InventoryPlayer playerInventory) {
        super(container)
        this.playerInventory = playerInventory
    }

    /**
     * Draws the screen and all the components in it.
     */
    @Override
    void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground()
        super.drawScreen(mouseX, mouseY, partialTicks)
        this.renderHoveredToolTip(mouseX, mouseY)
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
        String name = I18n.format(ModBlocks.entityLinkBox.getUnlocalizedName() + ".name")
        fontRenderer.drawString(name, 8, 6, 0x404040)
        fontRenderer.drawString(playerInventory.getDisplayName().getUnformattedText(), 8, ySize - 94, 0x404040)
    }

}
