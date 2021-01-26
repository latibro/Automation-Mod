package latibro.automation.core.context.world

import latibro.automation.core.api.world.WorldAPI
import latibro.automation.core.context.Context
import latibro.automation.core.context.entity.collection.LoadedEntityCollectionContextProvider
import latibro.automation.core.context.position.PositionContext
import latibro.automation.core.context.server.ServerContextProvider

interface WorldContext extends Context<WorldAPI>, ServerContextProvider, LoadedEntityCollectionContextProvider {

    PositionContext getPositionContextByCoordinate(double x, double y, double z)

}
