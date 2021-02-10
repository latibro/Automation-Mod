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
    double getX() {
        return context.x
    }

    @Override
    double getY() {
        return context.y
    }

    @Override
    double getZ() {
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
    double getDistanceToCoordinate(double x, double y, double z) {
        return context.getDistanceToCoordinate((int) x, (int) y, (int) z)
    }

}
