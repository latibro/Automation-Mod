package latibro.automation.core.api.location

import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.api.entity.EntityGroupAPI
import latibro.automation.core.api.world.WorldAPI
import latibro.automation.core.context.location.LocationContext

final class LocationAPIImpl implements LocationAPI, ContextAPI {

    private final LocationContext context

    LocationAPIImpl(LocationContext context) {
        this.context = Objects.requireNonNull(context)
    }

    @Override
    LocationContext getContext() {
        return context
    }

    @Override
    boolean isLoaded() {
        return context.isLoaded()
    }

    @Override
    Number getX() {
        return context.x
    }

    @Override
    Number getY() {
        return context.y
    }

    @Override
    Number getZ() {
        return context.z
    }

    @Override
    WorldAPI getWorld() {
        return APIRegistry.getAPI(context.worldContext) as WorldAPI
    }

    @Override
    EntityGroupAPI getEntities() {
        return APIRegistry.getAPI(context.entityGroupContext) as EntityGroupAPI
    }

    @Override
    Number getDistanceToCoordinate(Number x, Number y, Number z) {
        return context.getDistanceToCoordinate(x as int, y as int, z as int)
    }

}
