package latibro.automation.proxy

import groovy.transform.CompileStatic
import latibro.automation.AutomationMod
import net.minecraftforge.fml.common.network.NetworkRegistry
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper

@CompileStatic
class NetworkProxy {

    private static final SimpleNetworkWrapper simpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(AutomationMod.MODID)

    static void registerPackets() {
        AutomationMod.logger.debug("NetworkProxy.registerPackets")
    }

    static void sendMessageToServer(IMessage message) {
        simpleNetworkWrapper.sendToServer(message)
    }

}
