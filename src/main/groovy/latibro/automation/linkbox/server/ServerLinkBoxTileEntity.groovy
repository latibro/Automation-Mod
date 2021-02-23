package latibro.automation.linkbox.server

import groovy.transform.CompileStatic
import latibro.automation.api.link.server.ServerLinkAPI
import latibro.automation.core.LinkType
import latibro.automation.core.api.APIRegistry
import latibro.automation.core.peripheral.PeripheralTileEntity
import latibro.automation.nativeimpl.context.server.CoreServerLinkContext
import net.minecraft.server.MinecraftServer

@CompileStatic
class ServerLinkBoxTileEntity extends PeripheralTileEntity {

    @Override
    String getComponentName() {
        return "server_link"
    }

    @Override
    protected ServerLinkAPI getPeripheralAPI() {
        def linkBox = this
        def linkContext = new CoreServerLinkContext() {

            @Override
            MinecraftServer getNativeServer() {
                return linkBox.world.minecraftServer
            }

            @Override
            LinkType getLinkType() {
                return LinkType.STATIC
            }

        }
        def linkAPI = APIRegistry.getAPI(linkContext) as ServerLinkAPI
        return linkAPI
    }

}
