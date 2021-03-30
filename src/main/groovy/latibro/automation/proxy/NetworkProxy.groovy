package latibro.automation.proxy

import groovy.transform.CompileStatic
import latibro.automation.AutomationMod
import latibro.automation.linkbox.data.DataBoxMessage
import latibro.automation.linkbox.entity.EntityLinkCardMessage
import latibro.automation.linkbox.location.LocationLinkCardMessage
import latibro.automation.linkbox.redstone.RedstoneBoxMessage
import net.minecraftforge.fml.common.network.NetworkRegistry
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
import net.minecraftforge.fml.relauncher.Side

@CompileStatic
class NetworkProxy {

    private static final SimpleNetworkWrapper simpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(AutomationMod.MODID)

    static void registerPackets() {
        AutomationMod.logger.debug("NetworkProxy.registerPackets")

        simpleNetworkWrapper.registerMessage(DataBoxMessage.Handler, DataBoxMessage, 1, Side.SERVER)
        simpleNetworkWrapper.registerMessage(RedstoneBoxMessage.Handler, RedstoneBoxMessage, 2, Side.SERVER)
        simpleNetworkWrapper.registerMessage(LocationLinkCardMessage.Handler, LocationLinkCardMessage, 3, Side.SERVER)
        simpleNetworkWrapper.registerMessage(EntityLinkCardMessage.Handler, EntityLinkCardMessage, 4, Side.SERVER)
    }

    static void sendMessageToServer(IMessage message) {
        simpleNetworkWrapper.sendToServer(message)
    }

}
