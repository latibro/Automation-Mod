package latibro.automation.core.api.location

import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.entity.EntityGroupAPI
import latibro.automation.core.api.world.WorldAPI
import latibro.automation.core.context.location.LocationContext

final class LocationAPIImpl implements LocationAPI {

    private final LocationContext context

    LocationAPIImpl(LocationContext context) {
        this.context = Objects.requireNonNull(context)
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
        return (WorldAPI) APIRegistry.getContextAPI(context.worldContext)
    }

    @Override
    EntityGroupAPI getEntities() {
        return (EntityGroupAPI) APIRegistry.getContextAPI(context.entityGroupContext)
    }

    @Override
    Number getDistanceToCoordinate(Number x, Number y, Number z) {
        return context.getDistanceToCoordinate((int) x, (int) y, (int) z)
    }

}
