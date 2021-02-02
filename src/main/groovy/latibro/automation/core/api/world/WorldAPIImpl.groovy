package latibro.automation.core.api.world

import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.entity.EntityCollectionAPI
import latibro.automation.core.api.location.LocationAPI
import latibro.automation.core.api.server.ServerAPI
import latibro.automation.core.context.world.WorldContext

final class WorldAPIImpl implements WorldAPI {

    private final WorldContext context

    WorldAPIImpl(WorldContext context) {
        this.context = Objects.requireNonNull(context)
    }

    @Override
    ServerAPI getServer() {
        return (ServerAPI) APIRegistry.getContextAPI(context.serverContext)
    }

    @Override
    EntityCollectionAPI getLoadedEntities() {
        return (EntityCollectionAPI) APIRegistry.getContextAPI(context.loadedEntityCollectionContext)
    }

    @Override
    LocationAPI getLocationByCoordinate(double x, double y, double z) {
        return (LocationAPI) APIRegistry.getContextAPI(context.getLocationContextByCoordinate((int) x, (int) y, (int) z))
    }

}
