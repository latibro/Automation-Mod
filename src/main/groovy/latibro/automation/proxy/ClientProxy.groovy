package latibro.automation.proxy

import latibro.automation.ModBlocks
import latibro.automation.ModItems
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side

@Mod.EventBusSubscriber(Side.CLIENT)
class ClientProxy extends CommonProxy {

    @SubscribeEvent
    static void registerModels(ModelRegistryEvent event) {
        ModBlocks.initModels()
        ModItems.initModels()
    }

}
