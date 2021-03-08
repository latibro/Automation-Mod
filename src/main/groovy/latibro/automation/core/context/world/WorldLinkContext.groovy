package latibro.automation.core.context.world

import latibro.automation.core.context.entity.multi.EntityMultiLinkContext
import latibro.automation.core.context.link.SingleLinkContext
import latibro.automation.core.context.location.LocationLinkContext
import latibro.automation.core.context.server.ServerLinkContext
import latibro.automation.core.context.tileentity.multi.TileEntityMultiLinkContext

interface WorldLinkContext extends SingleLinkContext {

    String getName()

    ServerLinkContext getServer()

    EntityMultiLinkContext getLoadedEntities()

    TileEntityMultiLinkContext getLoadedTileEntities()

    LocationLinkContext getLocationByCoordinates(int x, int y, int z)

}
