package latibro.automation.core.context.world

import latibro.automation.core.context.Context
import latibro.automation.core.context.entity.group.LoadedEntitiesContextProvider
import latibro.automation.core.context.location.LocationContext
import latibro.automation.core.context.server.ServerContextProvider

interface WorldContext extends Context, ServerContextProvider, LoadedEntitiesContextProvider {

    LocationContext getLocationContextByCoordinate(int x, int y, int z)

}
