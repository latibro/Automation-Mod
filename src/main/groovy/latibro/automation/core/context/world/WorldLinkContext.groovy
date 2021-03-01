package latibro.automation.core.context.world

import latibro.automation.core.context.entity.multi.EntityMultiLinkContext
import latibro.automation.core.context.link.SingleLinkContext
import latibro.automation.core.context.location.LocationLinkContext
import latibro.automation.core.context.server.ServerLinkContext

interface WorldLinkContext extends SingleLinkContext {

    ServerLinkContext getServer()

    EntityMultiLinkContext getLoadedEntities()

    LocationLinkContext getLocationByCoordinates(int x, int y, int z)

}
