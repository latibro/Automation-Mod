package latibro.automation.proxy

import groovy.transform.CompileStatic
import latibro.automation.AutomationMod
import latibro.automation.linkbox.data.DataBoxMessage
import net.minecraftforge.fml.common.network.NetworkRegistry
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
import net.minecraftforge.fml.relauncher.Side

@CompileStatic
class NetworkProxy {

    private static final SimpleNetworkWrapper simpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(AutomationMod.MODID);

    static void registerPackets() {
        AutomationMod.logger.info("registerPackets")
        simpleNetworkWrapper.registerMessage(DataBoxMessage.DataBoxMessageHandler, DataBoxMessage, 0, Side.SERVER)
    }

    static void sendMessageToServer(IMessage message) {
        AutomationMod.logger.info("sendMessageToServer {}", message)
        simpleNetworkWrapper.sendToServer(message)
    }

}
