package latibro.automation.linkbox.data

import groovy.transform.CompileStatic
import latibro.automation.ModCreativeTabs
import latibro.automation.proxy.ScreenProxy
import net.minecraft.block.Block
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

import javax.annotation.Nullable

@CompileStatic
class DataBoxBlock extends Block implements ITileEntityProvider {

    DataBoxBlock() {
        super(Material.IRON)
        setRegistryName("data-box")
        setUnlocalizedName("automation.data-box")
        setCreativeTab(ModCreativeTabs.DEFAULT)
    }

    @SideOnly(Side.CLIENT)
    void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"))
    }

    @Nullable
    @Override
    TileEntity createNewTileEntity(World world, int meta) {
        return new DataBoxTileEntity()
    }

    @Override
    boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true
        }

        def tileEntity = world.getTileEntity(pos)
        ScreenProxy.openBlockEntityScreen(tileEntity, player)
        return true
    }

}
