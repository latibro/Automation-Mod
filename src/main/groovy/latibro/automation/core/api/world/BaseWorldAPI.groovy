package latibro.automation.core.api.world

import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.api.entity.EntityGroupAPI
import latibro.automation.core.api.location.LocationAPI
import latibro.automation.core.api.server.ServerAPI
import latibro.automation.core.context.world.WorldContext

class BaseWorldAPI implements WorldAPI, ContextAPI {

    private final WorldContext context

    BaseWorldAPI(WorldContext context) {
        this.context = Objects.requireNonNull(context)
    }

    @Override
    WorldContext getContext() {
        return context
    }

    @Override
    ServerAPI getServer() {
        return APIRegistry.getAPI(context.serverContext) as ServerAPI
    }

    @Override
    EntityGroupAPI getLoadedEntities() {
        return APIRegistry.getAPI(context.loadedEntitiesContext) as EntityGroupAPI
    }

    @Override
    LocationAPI getLocationByCoordinates(Number x, Number y, Number z) {
        return APIRegistry.getAPI(context.getLocationContextByCoordinates(x as int, y as int, z as int)) as LocationAPI
    }

}
