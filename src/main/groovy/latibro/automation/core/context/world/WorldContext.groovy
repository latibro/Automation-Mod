package latibro.automation.core.context.world

import latibro.automation.core.context.Context
import latibro.automation.core.context.entity.collection.LoadedEntityCollectionContextProvider
import latibro.automation.core.context.location.LocationContext
import latibro.automation.core.context.server.ServerContextProvider

interface WorldContext extends Context, ServerContextProvider, LoadedEntityCollectionContextProvider {

    LocationContext getLocationContextByCoordinate(int x, int y, int z)

}
