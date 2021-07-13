package latibro.automation.base.server.card

import groovy.transform.CompileStatic
import latibro.automation.ModCreativeTabs
import latibro.automation.core.source.card.item.SourceCardItem
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.ItemStack
import net.minecraft.server.MinecraftServer
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.common.FMLCommonHandler
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@CompileStatic
class ServerSourceCardItem extends SourceCardItem {

    static final ServerSourceCardItem INSTANCE = new ServerSourceCardItem()

    ServerSourceCardItem() {
        setRegistryName("server-source-card")
        setUnlocalizedName("automation.server-source-card")
        setCreativeTab(ModCreativeTabs.DEFAULT)
        setMaxStackSize(1)
    }

    @SideOnly(Side.CLIENT)
    void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"))
    }

    MinecraftServer getSourceObject(ItemStack itemStack) {
        return FMLCommonHandler.instance().getMinecraftServerInstance()
    }

}
