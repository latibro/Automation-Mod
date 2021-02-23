package latibro.automation.linkbox.world

import groovy.transform.CompileStatic
import latibro.automation.api.link.world.WorldLinkAPI
import latibro.automation.core.LinkType
import latibro.automation.core.api.APIRegistry
import latibro.automation.core.peripheral.PeripheralTileEntity
import latibro.automation.nativeimpl.context.world.CoreWorldLinkContext
import net.minecraft.world.World

@CompileStatic
class WorldLinkBoxTileEntity extends PeripheralTileEntity {

    @Override
    String getComponentName() {
        return "world_link"
    }

    @Override
    protected WorldLinkAPI getPeripheralAPI() {
        def linkBox = this
        def linkContext = new CoreWorldLinkContext() {

            @Override
            World getNativeWorld() {
                return linkBox.world
            }

            @Override
            LinkType getLinkType() {
                return LinkType.STATIC
            }

        }
        def linkAPI = APIRegistry.getAPI(linkContext) as WorldLinkAPI
        return linkAPI
    }

}
