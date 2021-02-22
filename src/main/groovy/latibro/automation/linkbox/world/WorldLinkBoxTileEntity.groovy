package latibro.automation.linkbox.world

import groovy.transform.CompileStatic
import latibro.automation.core.api.APIRegistry
import latibro.automation.api.link.world.WorldLinkAPI
import latibro.automation.core.peripheral.PeripheralTileEntity
import latibro.automation.nativeimpl.context.tileentity.NativeStaticTileEntityContext

@CompileStatic
class WorldLinkBoxTileEntity extends PeripheralTileEntity {

    @Override
    String getComponentName() {
        return "world_link"
    }

    @Override
    protected WorldLinkAPI getPeripheralAPI() {
        def tileEntityContext = new NativeStaticTileEntityContext(this)
        return APIRegistry.getAPI(tileEntityContext.worldContext) as WorldLinkAPI
    }

}
