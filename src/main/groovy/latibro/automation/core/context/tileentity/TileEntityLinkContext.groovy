package latibro.automation.core.context.tileentity

import latibro.automation.core.context.link.SingleLinkContext
import latibro.automation.core.context.location.LocationLinkContext

interface TileEntityLinkContext extends SingleLinkContext {

    boolean isLoaded()

    LocationLinkContext getLocation()

    String getType()

}
