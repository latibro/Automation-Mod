package latibro.automation.linkbox.data

import groovy.transform.CompileStatic
import latibro.automation.ModCreativeTabs
import net.minecraft.block.Block
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

import javax.annotation.Nullable

@CompileStatic
class DataBoxBlock extends Block implements ITileEntityProvider {

    DataBoxBlock() {
        super(Material.IRON)
        setRegistryName("data_box")
        setUnlocalizedName("automation.data_box")
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

}
