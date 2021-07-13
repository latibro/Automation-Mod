package latibro.automation

import latibro.automation.computer.relaybox.block.ComputerRelayBoxBlock
import latibro.automation.computer.relaybox.block.ComputerRelayBoxBlockEntity
import latibro.automation.linkbox.data.DataBoxBlock
import latibro.automation.linkbox.data.DataBoxTileEntity
import latibro.automation.linkbox.redstone.RedstoneBoxBlock
import latibro.automation.linkbox.redstone.RedstoneBoxTileEntity
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.registries.IForgeRegistry

class ModBlocks {

    @GameRegistry.ObjectHolder("automation:data-box")
    static DataBoxBlock dataBox

    @GameRegistry.ObjectHolder("automation:redstone-box")
    static RedstoneBoxBlock redstoneBox

    static void registerBlocks(IForgeRegistry<Block> registry) {
        AutomationMod.logger.debug("ModBlocks.registerBlocks")

        registerBlock(registry, new DataBoxBlock(), DataBoxTileEntity)
        registerBlock(registry, new RedstoneBoxBlock(), RedstoneBoxTileEntity)

        registerBlock(registry, ComputerRelayBoxBlock.INSTANCE, ComputerRelayBoxBlockEntity)
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

        registerItem(registry, dataBox)
        registerItem(registry, redstoneBox)

        registerItem(registry, ComputerRelayBoxBlock.INSTANCE)
    }

    static registerItem(IForgeRegistry<Item> registry, Block block) {
        def item = new ItemBlock(block)
        item.setRegistryName(block.getRegistryName())
        ModItems.registerItem(registry, item)
    }

    @SideOnly(Side.CLIENT)
    static void initModels() {
        AutomationMod.logger.debug("ModBlocks.initModels")

        dataBox.initModel()
        redstoneBox.initModel()

        ComputerRelayBoxBlock.INSTANCE.initModel()
    }

}
