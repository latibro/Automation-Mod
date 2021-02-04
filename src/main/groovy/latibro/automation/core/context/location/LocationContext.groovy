package latibro.automation.core.context.location

import latibro.automation.core.api.location.LocationAPI
import latibro.automation.core.context.Context
import latibro.automation.core.context.entity.collection.EntityCollectionContext
import latibro.automation.core.context.world.WorldContextProvider

interface LocationContext extends Context<LocationAPI>, WorldContextProvider {

    boolean isLoaded()

    int getX()

    int getY()

    int getZ()

    EntityCollectionContext getEntityCollectionContext()

    double getDistanceToCoordinate(int x, int y, int z)

}