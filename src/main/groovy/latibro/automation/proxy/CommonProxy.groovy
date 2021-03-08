package latibro.automation.proxy

import dan200.computercraft.api.ComputerCraftAPI
import latibro.automation.AutomationMod
import latibro.automation.ModBlocks
import latibro.automation.core.api.APIRegistry
import latibro.automation.core.context.ContextRegistry
import latibro.automation.integration.computercraft.TileEntityPeripheralProvider
import latibro.automation.integration.immersiverailroading.ImmersiveRailroadingAPIProvider
import latibro.automation.integration.immersiverailroading.ImmersiveRailroadingContextProvider
import latibro.automation.linkbox.data.DataBoxBlock
import latibro.automation.linkbox.data.DataBoxTileEntity
import latibro.automation.linkbox.entity.EntityLinkBoxBlock
import latibro.automation.linkbox.entity.EntityLinkBoxTileEntity
import latibro.automation.linkbox.entity.EntityLinkCardItem
import latibro.automation.linkbox.location.LocationLinkBoxBlock
import latibro.automation.linkbox.location.LocationLinkBoxTileEntity
import latibro.automation.linkbox.location.LocationLinkCardItem
import latibro.automation.linkbox.server.ServerLinkBoxBlock
import latibro.automation.linkbox.server.ServerLinkBoxTileEntity
import latibro.automation.linkbox.world.WorldLinkBoxBlock
import latibro.automation.linkbox.world.WorldLinkBoxTileEntity
import latibro.automation.nativeimpl.context.chunk.EntityCoreChunkLinkContext
import latibro.devoplment.DebugToolItem
import latibro.devoplment.EntityMountToolItem
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.common.ForgeChunkManager
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.network.NetworkRegistry
import net.minecraftforge.fml.common.registry.GameRegistry

@Mod.EventBusSubscriber
class CommonProxy {

    void preInit(FMLPreInitializationEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler(AutomationMod.instance, new ScreenProxy())
    }

    void init(FMLInitializationEvent e) {
        ForgeChunkManager.setForcedChunkLoadingCallback(AutomationMod.instance, new ForgeChunkManager.LoadingCallback() {
            @Override
            void ticketsLoaded(List<ForgeChunkManager.Ticket> tickets, World world) {
                AutomationMod.logger.info("Chunk force load tickets loaded {}", tickets)
            }
        })
        MinecraftForge.EVENT_BUS.register(EntityCoreChunkLinkContext)

        if (Loader.isModLoaded("computercraft")) {
            ComputerCraftAPI.registerPeripheralProvider(new TileEntityPeripheralProvider())
        }

        if (Loader.isModLoaded("immersiverailroading")) {
            ContextRegistry.register(new ImmersiveRailroadingContextProvider())
            APIRegistry.register(new ImmersiveRailroadingAPIProvider())
        }
    }

    void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent
    static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new ServerLinkBoxBlock())
        GameRegistry.registerTileEntity(ServerLinkBoxTileEntity.class, new ResourceLocation("server_link_box"))

        event.getRegistry().register(new WorldLinkBoxBlock())
        GameRegistry.registerTileEntity(WorldLinkBoxTileEntity.class, new ResourceLocation("world_link_box"))

        event.getRegistry().register(new EntityLinkBoxBlock())
        GameRegistry.registerTileEntity(EntityLinkBoxTileEntity.class, new ResourceLocation("entity_link_box"))

        event.getRegistry().register(new LocationLinkBoxBlock())
        GameRegistry.registerTileEntity(LocationLinkBoxTileEntity.class, new ResourceLocation("location_link_box"))

        event.getRegistry().register(new DataBoxBlock())
        GameRegistry.registerTileEntity(DataBoxTileEntity.class, new ResourceLocation("data_box"))
    }

    @SubscribeEvent
    static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(ModBlocks.serverLinkBox).setRegistryName(ModBlocks.serverLinkBox.getRegistryName()))
        event.getRegistry().register(new ItemBlock(ModBlocks.worldLinkBox).setRegistryName(ModBlocks.worldLinkBox.getRegistryName()))
        event.getRegistry().register(new ItemBlock(ModBlocks.entityLinkBox).setRegistryName(ModBlocks.entityLinkBox.getRegistryName()))
        event.getRegistry().register(new ItemBlock(ModBlocks.locationLinkBox).setRegistryName(ModBlocks.locationLinkBox.getRegistryName()))
        event.getRegistry().register(new ItemBlock(ModBlocks.dataBox).setRegistryName(ModBlocks.dataBox.getRegistryName()))

        event.getRegistry().register(new EntityLinkCardItem())
        event.getRegistry().register(new LocationLinkCardItem())

        event.getRegistry().register(new DebugToolItem())
        event.getRegistry().register(new EntityMountToolItem())
    }

}
