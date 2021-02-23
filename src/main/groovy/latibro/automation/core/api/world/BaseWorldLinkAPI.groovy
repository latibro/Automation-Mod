package latibro.automation.core.api.world

import latibro.automation.api.link.entity.EntityMultiLinkAPI
import latibro.automation.api.link.location.LocationLinkAPI
import latibro.automation.api.link.server.ServerLinkAPI
import latibro.automation.api.link.world.WorldLinkAPI
import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.context.world.WorldLinkContext

class BaseWorldLinkAPI implements WorldLinkAPI, ContextAPI {

    private final WorldLinkContext context

    BaseWorldLinkAPI(WorldLinkContext context) {
        this.context = Objects.requireNonNull(context)
    }

    @Override
    WorldLinkContext getContext() {
        return context
    }

    @Override
    Boolean isLinked() {
        return context.isLinked()
    }

    @Override
    String getLinkType() {
        return context.getLinkType()
    }

    @Override
    ServerLinkAPI getServer() {
        return APIRegistry.getAPI(context.server) as ServerLinkAPI
    }

    @Override
    EntityMultiLinkAPI getLoadedEntities() {
        return APIRegistry.getAPI(context.loadedEntities) as EntityMultiLinkAPI
    }

    @Override
    LocationLinkAPI getLocationByCoordinates(Number x, Number y, Number z) {
        return APIRegistry.getAPI(context.getLocationByCoordinates(x as int, y as int, z as int)) as LocationLinkAPI
    }

}
