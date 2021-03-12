package latibro.automation.core.context.entity


import latibro.automation.core.context.entity.multi.EntityMultiLinkContext
import latibro.automation.core.context.link.SingleLinkContext
import latibro.automation.core.context.location.LocationLinkContext
import latibro.automation.core.context.server.ServerLinkContext
import latibro.automation.core.context.tileentity.multi.TileEntityMultiLinkContext

interface EntityLinkContext extends SingleLinkContext {

    UUID getUUID()

    boolean isLoaded()

    LocationLinkContext getLocation()

    String getName()

    String getType()

    ServerLinkContext getServer()

    EntityMultiLinkContext getNearbyEntities(double range)

    EntityMultiLinkContext getNearbyEntities(double range, boolean includeBoundingBoxes)

    TileEntityMultiLinkContext getNearbyTileEntities(double range)

    TileEntityMultiLinkContext getNearbyTileEntities(double range, boolean includeBoundingBoxes)

}
