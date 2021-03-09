package latibro.automation.proxy

import groovy.transform.CompileStatic
import latibro.automation.AutomationMod
import latibro.automation.ModBlocks
import latibro.automation.ModItems
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side

@CompileStatic
@Mod.EventBusSubscriber(Side.CLIENT)
class ClientProxy extends CommonProxy {

    @SubscribeEvent
    static void registerModels(ModelRegistryEvent event) {
        AutomationMod.logger.debug("ClientProxy.registerModels")

        ModBlocks.initModels()
        ModItems.initModels()
    }

}
