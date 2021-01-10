package latibro.automation.proxy

import dan200.computercraft.api.ComputerCraftAPI
import latibro.automation.ModBlocks
import latibro.automation.integration.computercraft.TileEntityPeripheralProvider
import latibro.automation.interfacebox.InterfaceBoxBlock
import latibro.automation.interfacebox.InterfaceBoxTileEntity
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.util.ResourceLocation
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.GameRegistry

@Mod.EventBusSubscriber
class CommonProxy {

    void preInit(FMLPreInitializationEvent e) {
    }

    void init(FMLInitializationEvent e) {
        ComputerCraftAPI.registerPeripheralProvider(new TileEntityPeripheralProvider())
        ComputerCraftAPI.registerAPIFactory()
    }

    void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent
    static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new InterfaceBoxBlock())
        GameRegistry.registerTileEntity(InterfaceBoxTileEntity.class, new ResourceLocation("interface_box"))
    }

    @SubscribeEvent
    static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(ModBlocks.interfaceBox).setRegistryName(ModBlocks.interfaceBox.getRegistryName()))
    }

}
