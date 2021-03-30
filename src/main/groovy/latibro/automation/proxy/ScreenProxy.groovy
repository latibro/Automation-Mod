package latibro.automation.proxy

import groovy.transform.CompileStatic
import latibro.automation.AutomationMod
import latibro.automation.linkbox.data.DataBoxContainer
import latibro.automation.linkbox.data.DataBoxScreen
import latibro.automation.linkbox.data.DataBoxTileEntity
import latibro.automation.linkbox.entity.*
import latibro.automation.linkbox.location.*
import latibro.automation.linkbox.redstone.RedstoneBoxContainer
import latibro.automation.linkbox.redstone.RedstoneBoxScreen
import latibro.automation.linkbox.redstone.RedstoneBoxTileEntity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.IGuiHandler

@CompileStatic
class ScreenProxy implements IGuiHandler {

    static final int TILE_ENTITY_SCREEN = 1
    static final int HELD_ITEM_SCREEN = 2

    static void openTileEntityScreen(EntityPlayer player, TileEntity tileEntity) {
        def world = tileEntity.world
        def pos = tileEntity.pos
        player.openGui(AutomationMod.instance, ScreenProxy.TILE_ENTITY_SCREEN, world, pos.getX(), pos.getY(), pos.getZ())
    }

    static void openHeldItemScreen(EntityPlayer player) {
        player.openGui(AutomationMod.instance, ScreenProxy.HELD_ITEM_SCREEN, null, 0, 0, 0)
    }

    @Override
    Object getServerGuiElement(int screenId, EntityPlayer player, World world, int x, int y, int z) {
        if (screenId == TILE_ENTITY_SCREEN) {
            def pos = new BlockPos(x, y, z)
            def tileEntity = world.getTileEntity(pos)

            if (tileEntity instanceof EntityLinkBoxTileEntity) {
                return new EntityLinkBoxContainer(tileEntity, player)
            }
            if (tileEntity instanceof LocationLinkBoxTileEntity) {
                return new LocationLinkBoxContainer(tileEntity, player)
            }
            if (tileEntity instanceof DataBoxTileEntity) {
                return new DataBoxContainer(tileEntity)
            }
            if (tileEntity instanceof RedstoneBoxTileEntity) {
                return new RedstoneBoxContainer(tileEntity)
            }
        }
        if (screenId == HELD_ITEM_SCREEN) {
            def itemStack = player.getHeldItem(EnumHand.MAIN_HAND)
            def item = itemStack.item

            if (item instanceof LocationLinkCardItem) {
                return new LocationLinkCardContainer(player)
            }
            if (item instanceof EntityLinkCardItem) {
                return new EntityLinkCardContainer(player)
            }
        }
        return null
    }

    @Override
    Object getClientGuiElement(int screenId, EntityPlayer player, World world, int x, int y, int z) {
        def serverGuiElement = getServerGuiElement(screenId, player, world, x, y, z)

        if (serverGuiElement instanceof EntityLinkBoxContainer) {
            return new EntityLinkBoxScreen(serverGuiElement)
        }
        if (serverGuiElement instanceof EntityLinkCardContainer) {
            return new EntityLinkCardScreen(serverGuiElement)
        }
        if (serverGuiElement instanceof LocationLinkBoxContainer) {
            return new LocationLinkBoxScreen(serverGuiElement)
        }
        if (serverGuiElement instanceof LocationLinkCardContainer) {
            return new LocationLinkCardScreen(serverGuiElement)
        }
        if (serverGuiElement instanceof DataBoxContainer) {
            return new DataBoxScreen(serverGuiElement)
        }
        if (serverGuiElement instanceof RedstoneBoxContainer) {
            return new RedstoneBoxScreen(serverGuiElement)
        }
        return null
    }

}
