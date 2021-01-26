package latibro.automation.core.api.world

import latibro.automation.core.api.entity.EntityCollectionAPI
import latibro.automation.core.api.position.PositionAPI
import latibro.automation.core.api.server.ServerAPI
import latibro.automation.core.context.world.WorldContext

class WorldAPIImpl implements WorldAPI {

    private final WorldContext context

    WorldAPIImpl(WorldContext context) {
        this.context = Objects.requireNonNull(context)
    }

    @Override
    ServerAPI getServer() {
        return context.serverContext.getAPI()
    }

    @Override
    EntityCollectionAPI getLoadedEntityCollection() {
        return context.loadedEntityCollectionContext.getAPI()
    }

    @Override
    PositionAPI getPositionByCoordinate(double x, double y, double z) {
        return context.getPositionContextByCoordinate(x, y, z).getAPI()
    }

}
