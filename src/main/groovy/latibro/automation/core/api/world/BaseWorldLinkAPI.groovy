package latibro.automation.core.api.world


import latibro.automation.api.link.world.WorldLinkAPI
import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.ContextAPI
import latibro.automation.api.link.entity.EntityMultiLinkAPI
import latibro.automation.api.link.location.LocationLinkAPI
import latibro.automation.api.link.server.ServerLinkAPI
import latibro.automation.core.context.world.WorldContext

class BaseWorldLinkAPI implements WorldLinkAPI, ContextAPI {

    private final WorldContext context

    BaseWorldLinkAPI(WorldContext context) {
        this.context = Objects.requireNonNull(context)
    }

    @Override
    WorldContext getContext() {
        return context
    }

    @Override
    Boolean isLinked() {
        //TODO implement
        throw new RuntimeException("Not yet implemented")
    }

    @Override
    String getLinkType() {
        //TODO implement
        throw new RuntimeException("Not yet implemented")
    }

    @Override
    Boolean isLoaded() {
        //TODO implement
        throw new RuntimeException("Not yet implemented")
    }

    @Override
    ServerLinkAPI getServer() {
        return APIRegistry.getAPI(context.serverContext) as ServerLinkAPI
    }

    @Override
    EntityMultiLinkAPI getLoadedEntities() {
        return APIRegistry.getAPI(context.loadedEntitiesContext) as EntityMultiLinkAPI
    }

    @Override
    LocationLinkAPI getLocationByCoordinates(Number x, Number y, Number z) {
        return APIRegistry.getAPI(context.getLocationContextByCoordinates(x as int, y as int, z as int)) as LocationLinkAPI
    }

}
