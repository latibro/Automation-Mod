package latibro.automation.proxy;

import dan200.computercraft.api.ComputerCraftAPI;
import latibro.automation.ModBlocks;
import latibro.automation.integration.computercraft.SourceBoxPeripheralProvider;
import latibro.automation.source.SourceBoxBlock;
import latibro.automation.source.SourceBoxTileEntity;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
    }

    public void init(FMLInitializationEvent e) {
        ComputerCraftAPI.registerPeripheralProvider(new SourceBoxPeripheralProvider());
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new SourceBoxBlock());
        GameRegistry.registerTileEntity(SourceBoxTileEntity.class, new ResourceLocation("source_box"));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(ModBlocks.sourceBox).setRegistryName(ModBlocks.sourceBox.getRegistryName()));
    }

}
