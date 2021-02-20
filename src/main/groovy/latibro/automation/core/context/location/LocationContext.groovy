package latibro.automation.core.context.location


import latibro.automation.core.context.Context
import latibro.automation.core.context.entity.group.EntityGroupContext
import latibro.automation.core.context.world.WorldContextProvider

interface LocationContext extends Context, WorldContextProvider {

    boolean isLoaded()

    int getX()

    int getY()

    int getZ()

    EntityGroupContext getEntities()

    EntityGroupContext getEntities(boolean includeBoudingBoxes)

    double getDistanceToCoordinates(int x, int y, int z)

}