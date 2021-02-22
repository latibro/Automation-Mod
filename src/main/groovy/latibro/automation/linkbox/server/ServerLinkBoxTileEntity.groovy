package latibro.automation.linkbox.server

import groovy.transform.CompileStatic
import latibro.automation.core.api.APIRegistry
import latibro.automation.api.link.server.ServerLinkAPI
import latibro.automation.core.peripheral.PeripheralTileEntity
import latibro.automation.nativeimpl.context.tileentity.NativeStaticTileEntityContext

@CompileStatic
class ServerLinkBoxTileEntity extends PeripheralTileEntity {

    @Override
    String getComponentName() {
        return "server_link"
    }

    @Override
    protected ServerLinkAPI getPeripheralAPI() {
        def tileEntityContext = new NativeStaticTileEntityContext(this)
        return APIRegistry.getAPI(tileEntityContext.worldContext.serverContext) as ServerLinkAPI
    }

}
