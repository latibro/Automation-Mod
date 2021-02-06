package latibro.automation.proxy

import groovy.transform.CompileStatic
import latibro.automation.linkbox.entity.EntityLinkBoxContainer
import latibro.automation.linkbox.entity.EntityLinkBoxScreen
import latibro.automation.linkbox.entity.EntityLinkBoxTileEntity
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
        TileEntity te = world.getTileEntity(pos)
        if (te instanceof EntityLinkBoxTileEntity) {
            return new EntityLinkBoxContainer(player.inventory, te)
        }
        return null
    }

    @Override
    Object getClientGuiElement(int screenId, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z)
        TileEntity te = world.getTileEntity(pos)
        if (te instanceof EntityLinkBoxTileEntity) {
            return new EntityLinkBoxScreen((EntityLinkBoxContainer) getServerGuiElement(screenId, player, world, x, y, z), player.inventory)
        }
        return null
    }

}
