package latibro.automation.core.context.tileentity


import latibro.automation.core.context.link.SingleLinkContext
import latibro.automation.core.context.location.LocationContext
import latibro.automation.core.context.world.WorldLinkContext

interface TileEntityLinkContext extends SingleLinkContext {

    WorldLinkContext getWorld()

    LocationContext getLocation()

}
