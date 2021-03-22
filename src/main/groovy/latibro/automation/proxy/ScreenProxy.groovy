package latibro.automation.proxy

import groovy.transform.CompileStatic
import latibro.automation.AutomationMod
import latibro.automation.linkbox.data.DataBoxContainer
import latibro.automation.linkbox.data.DataBoxScreen
import latibro.automation.linkbox.data.DataBoxTileEntity
import latibro.automation.linkbox.entity.EntityLinkBoxContainer
import latibro.automation.linkbox.entity.EntityLinkBoxScreen
import latibro.automation.linkbox.entity.EntityLinkBoxTileEntity
import latibro.automation.linkbox.location.LocationLinkBoxContainer
import latibro.automation.linkbox.location.LocationLinkBoxScreen
import latibro.automation.linkbox.location.LocationLinkBoxTileEntity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.IGuiHandler

@CompileStatic
class ScreenProxy implements IGuiHandler {

    static final int TILE_ENTITY_SCREEN = 1
    static final int ITEM_STACK_SCREEN = 2

    static void openTileEntityScreen(EntityPlayer player, TileEntity tileEntity) {
        def world = tileEntity.world
        def pos = tileEntity.pos
        player.openGui(AutomationMod.instance, ScreenProxy.TILE_ENTITY_SCREEN, world, pos.getX(), pos.getY(), pos.getZ())
    }

    static void openItemScreen(EntityPlayer player, ItemStack itemStack) {
        player.openGui(AutomationMod.instance, ScreenProxy.ITEM_STACK_SCREEN, null, 0, 0, 0)
    }

    @Override
    Object getServerGuiElement(int screenId, EntityPlayer player, World world, int x, int y, int z) {
        if (screenId == TILE_ENTITY_SCREEN) {
            BlockPos pos = new BlockPos(x, y, z)
            TileEntity tileEntity = world.getTileEntity(pos)

            if (tileEntity instanceof EntityLinkBoxTileEntity) {
                return new EntityLinkBoxContainer(tileEntity, player)
            }
            if (tileEntity instanceof LocationLinkBoxTileEntity) {
                return new LocationLinkBoxContainer(tileEntity, player)
            }
            if (tileEntity instanceof DataBoxTileEntity) {
                return new DataBoxContainer(tileEntity)
            }
        }
        if (screenId == ITEM_STACK_SCREEN) {
            ItemStack itemStack = player.getActiveItemStack()
        }
        return null
    }

    @Override
    Object getClientGuiElement(int screenId, EntityPlayer player, World world, int x, int y, int z) {
        def serverGuiElement = getServerGuiElement(screenId, player, world, x, y, z)

        if (serverGuiElement instanceof EntityLinkBoxContainer) {
            return new EntityLinkBoxScreen(serverGuiElement)
        }
        if (serverGuiElement instanceof LocationLinkBoxContainer) {
            return new LocationLinkBoxScreen(serverGuiElement)
        }
        if (serverGuiElement instanceof DataBoxContainer) {
            return new DataBoxScreen(serverGuiElement)
        }
        return null
    }

}
