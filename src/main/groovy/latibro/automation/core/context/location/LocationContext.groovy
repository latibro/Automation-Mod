package latibro.automation.core.context.location

import latibro.automation.core.context.entity.multi.EntityMultiLinkContext
import latibro.automation.core.context.link.SingleLinkContext
import latibro.automation.core.context.world.WorldLinkContext

interface LocationContext extends SingleLinkContext {

    boolean isLoaded()

    int getX()

    int getY()

    int getZ()

    WorldLinkContext getWorld()

    EntityMultiLinkContext getEntities()

    EntityMultiLinkContext getEntities(boolean includeBoundingBoxes)

    double getDistanceToCoordinates(int x, int y, int z)

}