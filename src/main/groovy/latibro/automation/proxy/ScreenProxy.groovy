package latibro.automation.proxy

import groovy.transform.CompileStatic
import latibro.automation.linkbox.entity.EntityLinkBoxContainer
import latibro.automation.linkbox.entity.EntityLinkBoxScreen
import latibro.automation.linkbox.entity.EntityLinkBoxTileEntity
import latibro.automation.linkbox.location.LocationLinkBoxContainer
import latibro.automation.linkbox.location.LocationLinkBoxScreen
import latibro.automation.linkbox.location.LocationLinkBoxTileEntity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.IGuiHandler

@CompileStatic
class ScreenProxy implements IGuiHandler {

    @Override
    Object getServerGuiElement(int screenId, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z)
        TileEntity tileEntity = world.getTileEntity(pos)
        if (tileEntity instanceof EntityLinkBoxTileEntity) {
            return new EntityLinkBoxContainer(player, tileEntity)
        }
        if (tileEntity instanceof LocationLinkBoxTileEntity) {
            return new LocationLinkBoxContainer(player, tileEntity)
        }
        return null
    }

    @Override
    Object getClientGuiElement(int screenId, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z)
        TileEntity tileEntity = world.getTileEntity(pos)
        if (tileEntity instanceof EntityLinkBoxTileEntity) {
            return new EntityLinkBoxScreen((EntityLinkBoxContainer) getServerGuiElement(screenId, player, world, x, y, z), player.inventory)
        }
        if (tileEntity instanceof LocationLinkBoxTileEntity) {
            return new LocationLinkBoxScreen((LocationLinkBoxContainer) getServerGuiElement(screenId, player, world, x, y, z), player.inventory)
        }
        return null
    }

}
