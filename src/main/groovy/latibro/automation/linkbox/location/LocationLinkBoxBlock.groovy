package latibro.automation.linkbox.location

import groovy.transform.CompileStatic
import latibro.automation.ModCreativeTabs
import latibro.automation.proxy.ScreenProxy
import net.minecraft.block.Block
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler

import javax.annotation.Nullable

@CompileStatic
class LocationLinkBoxBlock extends Block implements ITileEntityProvider {

    LocationLinkBoxBlock() {
        super(Material.IRON)
        setRegistryName("location_link_box")
        setUnlocalizedName("automation.location_link_box")
        setCreativeTab(ModCreativeTabs.DEFAULT)
    }

    @SideOnly(Side.CLIENT)
    void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"))
    }

    @Nullable
    @Override
    TileEntity createNewTileEntity(World world, int meta) {
        return new LocationLinkBoxTileEntity()
    }

    @Override
    boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true
        }

        def tileEntity = world.getTileEntity(pos)
        ScreenProxy.openTileEntityScreen(player, tileEntity)
        return true
    }

    @Override
    void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntity tile = world.getTileEntity(pos)
        IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH)
        ItemStack stack = itemHandler.getStackInSlot(0)
        if (!stack.isEmpty()) {
            EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack)
            world.spawnEntity(item)
        }
        super.breakBlock(world, pos, state)
    }

}
