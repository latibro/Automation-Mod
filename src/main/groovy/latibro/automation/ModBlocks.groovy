package latibro.automation

import latibro.automation.linkbox.data.DataBoxBlock
import latibro.automation.linkbox.data.DataBoxTileEntity
import latibro.automation.linkbox.entity.EntityLinkBoxBlock
import latibro.automation.linkbox.entity.EntityLinkBoxTileEntity
import latibro.automation.linkbox.location.LocationLinkBoxBlock
import latibro.automation.linkbox.location.LocationLinkBoxTileEntity
import latibro.automation.linkbox.redstone.RedstoneBoxBlock
import latibro.automation.linkbox.redstone.RedstoneBoxTileEntity
import latibro.automation.linkbox.server.ServerLinkBoxBlock
import latibro.automation.linkbox.server.ServerLinkBoxTileEntity
import latibro.automation.linkbox.world.WorldLinkBoxBlock
import latibro.automation.linkbox.world.WorldLinkBoxTileEntity
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistry

class ModBlocks {

    @GameRegistry.ObjectHolder("automation:server_link_box")
    static ServerLinkBoxBlock serverLinkBox

    @GameRegistry.ObjectHolder("automation:world_link_box")
    static WorldLinkBoxBlock worldLinkBox

    @GameRegistry.ObjectHolder("automation:entity_link_box")
    static EntityLinkBoxBlock entityLinkBox

    @GameRegistry.ObjectHolder("automation:location_link_box")
    static LocationLinkBoxBlock locationLinkBox

    @GameRegistry.ObjectHolder("automation:data_box")
    static DataBoxBlock dataBox

    @GameRegistry.ObjectHolder("automation:redstone_box")
    static RedstoneBoxBlock redstoneBox

    static void registerBlocks(IForgeRegistry<Block> registry) {
        AutomationMod.logger.debug("ModBlocks.registerBlocks")

        registerBlock(registry, new ServerLinkBoxBlock(), ServerLinkBoxTileEntity)
        registerBlock(registry, new WorldLinkBoxBlock(), WorldLinkBoxTileEntity)
        registerBlock(registry, new EntityLinkBoxBlock(), EntityLinkBoxTileEntity)
        registerBlock(registry, new LocationLinkBoxBlock(), LocationLinkBoxTileEntity)
        registerBlock(registry, new DataBoxBlock(), DataBoxTileEntity)
        registerBlock(registry, new RedstoneBoxBlock(), RedstoneBoxTileEntity)
    }

    static registerBlock(IForgeRegistry<Block> registry, Block block) {
        registry.register(block)
    }

    static registerBlock(IForgeRegistry<Block> registry, Block block, Class<? extends TileEntity> tileEntityClass) {
        registerBlock(registry, block)
        GameRegistry.registerTileEntity(tileEntityClass, block.getRegistryName())
    }

    static void registerItems(IForgeRegistry<Item> registry) {
        AutomationMod.logger.debug("ModBlocks.registerItems")

        registerItem(registry, serverLinkBox)
        registerItem(registry, worldLinkBox)
        registerItem(registry, entityLinkBox)
        registerItem(registry, locationLinkBox)
        registerItem(registry, dataBox)
        registerItem(registry, redstoneBox)
    }

    static registerItem(IForgeRegistry<Item> registry, Block block) {
        def item = new ItemBlock(block)
        item.setRegistryName(block.getRegistryName())
        ModItems.registerItem(registry, item)
    }

    @SideOnly(Side.CLIENT)
    static void initModels() {
        AutomationMod.logger.debug("ModBlocks.initModels")

        serverLinkBox.initModel()
        worldLinkBox.initModel()
        entityLinkBox.initModel()
        locationLinkBox.initModel()
        dataBox.initModel()
        redstoneBox.initModel()
    }

}
