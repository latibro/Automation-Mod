package latibro.automation.core.context.location


import latibro.automation.core.context.Context
import latibro.automation.core.context.entity.group.EntityGroupContext
import latibro.automation.core.context.world.WorldContextProvider

interface LocationContext extends Context, WorldContextProvider {

    boolean isLoaded()

    int getX()

    int getY()

    int getZ()

    EntityGroupContext getEntityGroupContext()

    double getDistanceToCoordinate(int x, int y, int z)

}