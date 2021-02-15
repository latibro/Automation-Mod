package latibro.automation.linkbox.world

import groovy.transform.CompileStatic
import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.world.WorldAPI
import latibro.automation.core.peripheral.PeripheralTileEntity
import latibro.automation.nativeimpl.context.tileentity.NativeStaticTileEntityContext

@CompileStatic
class WorldLinkBoxTileEntity extends PeripheralTileEntity {

    @Override
    String getComponentName() {
        return "world_link"
    }

    @Override
    protected WorldAPI getPeripheralAPI() {
        def tileEntityContext = new NativeStaticTileEntityContext(this)
        return APIRegistry.getAPI(tileEntityContext.worldContext) as WorldAPI
    }

}
