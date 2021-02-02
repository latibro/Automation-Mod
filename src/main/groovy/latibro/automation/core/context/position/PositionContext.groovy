package latibro.automation.core.context.position

import latibro.automation.core.api.position.PositionAPI
import latibro.automation.core.context.Context
import latibro.automation.core.context.entity.collection.EntityCollectionContext
import latibro.automation.core.context.world.WorldContextProvider

interface PositionContext extends Context<PositionAPI>, WorldContextProvider {

    boolean isLoaded()

    int getX()

    int getY()

    int getZ()

    EntityCollectionContext getEntityCollectionContext()

}