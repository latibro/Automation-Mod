package latibro.automation.core.context.location

import latibro.automation.core.context.chunk.ChunkLinkContext
import latibro.automation.core.context.entity.multi.EntityMultiLinkContext
import latibro.automation.core.context.link.SingleLinkContext
import latibro.automation.core.context.tileentity.multi.TileEntityMultiLinkContext
import latibro.automation.core.context.world.WorldLinkContext

interface LocationLinkContext extends SingleLinkContext {

    boolean isLoaded()

    int getX()

    int getY()

    int getZ()

    WorldLinkContext getWorld()

    ChunkLinkContext getChunk()

    EntityMultiLinkContext getEntities()

    EntityMultiLinkContext getEntities(boolean includeBoundingBoxes)

    EntityMultiLinkContext getNearbyEntities(double range)

    EntityMultiLinkContext getNearbyEntities(double range, boolean includeBoundingBoxes)

    TileEntityMultiLinkContext getTileEntities()

    TileEntityMultiLinkContext getNearbyTileEntities(double range)

    double getDistanceToCoordinates(int x, int y, int z)

}