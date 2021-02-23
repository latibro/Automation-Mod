package latibro.automation.linkbox.world

import groovy.transform.CompileStatic
import latibro.automation.api.link.world.WorldLinkAPI
import latibro.automation.core.api.APIRegistry
import latibro.automation.core.peripheral.PeripheralTileEntity
import latibro.automation.nativeimpl.context.tileentity.InstanceCoreTileEntityLinkContext

@CompileStatic
class WorldLinkBoxTileEntity extends PeripheralTileEntity {

    @Override
    String getComponentName() {
        return "world_link"
    }

    @Override
    protected WorldLinkAPI getPeripheralAPI() {
        def tileEntityContext = new InstanceCoreTileEntityLinkContext(this)
        return APIRegistry.getAPI(tileEntityContext.world) as WorldLinkAPI
    }

}
