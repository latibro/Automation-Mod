package latibro.automation.proxy

import groovy.transform.CompileStatic
import latibro.automation.AutomationMod
import latibro.automation.ModBlocks
import latibro.automation.ModItems
import latibro.automation.base.entity.reference.EntityReferenceSourceProvider
import latibro.automation.base.world.reference.WorldReferenceSourceProvider
import latibro.automation.core.api.APIRegistry
import latibro.automation.core.source.SourceRegistry
import latibro.automation.core.source.card.SourceCardSourceProvider
import latibro.automation.integration.computercraft.BlockEntityPeripheralProvider
import latibro.automation.integration.immersiverailroading.ImmersiveRailroadingLuaAPIProvider
import latibro.automation.integration.minecraft.MinecraftLuaAPIProvider
import latibro.automation.integration.universalmodcore.CustomEntitySourceProvider
import latibro.automation.linkbox.data.DataBoxAPIProvider
import latibro.automation.linkbox.redstone.RedstoneBoxAPIProvider
import latibro.automation.lua.api.LuaAPIRegistry
import latibro.automation.nativeimpl.context.chunk.EntityCoreChunkLinkContext
import net.minecraft.block.Block
import net.minecraft.item.Item
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

@CompileStatic
@Mod.EventBusSubscriber
class CommonProxy {

    void preInit(FMLPreInitializationEvent e) {
        AutomationMod.logger.debug("CommonProxy.preInit")

        NetworkRegistry.INSTANCE.registerGuiHandler(AutomationMod.instance, new ScreenProxy())

        NetworkProxy.registerPackets()
    }

    void init(FMLInitializationEvent e) {
        AutomationMod.logger.debug("CommonProxy.init")

        ForgeChunkManager.setForcedChunkLoadingCallback(AutomationMod.instance, new ForgeChunkManager.LoadingCallback() {
            @Override
            void ticketsLoaded(List<ForgeChunkManager.Ticket> tickets, World world) {
                AutomationMod.logger.info("Chunk force load tickets loaded {}", tickets)
            }
        })
        MinecraftForge.EVENT_BUS.register(EntityCoreChunkLinkContext)

        SourceRegistry.INSTANCE.register(new SourceCardSourceProvider())

        SourceRegistry.INSTANCE.register(new EntityReferenceSourceProvider())
        SourceRegistry.INSTANCE.register(new WorldReferenceSourceProvider())

        APIRegistry.register(new DataBoxAPIProvider())
        APIRegistry.register(new RedstoneBoxAPIProvider())

        LuaAPIRegistry.INSTANCE.register(new MinecraftLuaAPIProvider())

        if (Loader.isModLoaded("opencomputers")) {
            AutomationMod.logger.debug("Found OpenComputers")
        }

        if (Loader.isModLoaded("computercraft")) {
            AutomationMod.logger.debug("Found ComputerCraft")
            BlockEntityPeripheralProvider.init()
        }

        if (Loader.isModLoaded("universalmodcore")) {
            AutomationMod.logger.debug("Found Universal Mod Core")
            SourceRegistry.INSTANCE.register(new CustomEntitySourceProvider())
        }

        if (Loader.isModLoaded("immersiverailroading")) {
            AutomationMod.logger.debug("Found Immersive Railroading")
            LuaAPIRegistry.INSTANCE.register(new ImmersiveRailroadingLuaAPIProvider())
        }
    }

    void postInit(FMLPostInitializationEvent e) {
        AutomationMod.logger.debug("CommonProxy.postInit")
    }

    @SubscribeEvent
    static void registerBlocks(RegistryEvent.Register<Block> event) {
        AutomationMod.logger.debug("CommonProxy.registerBlocks")

        ModBlocks.registerBlocks(event.getRegistry())
    }

    @SubscribeEvent
    static void registerItems(RegistryEvent.Register<Item> event) {
        AutomationMod.logger.debug("CommonProxy.registerItems")

        ModBlocks.registerItems(event.getRegistry())
        ModItems.registerItems(event.getRegistry())
    }

}
