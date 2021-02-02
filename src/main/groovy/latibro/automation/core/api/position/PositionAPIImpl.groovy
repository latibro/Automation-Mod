package latibro.automation.core.api.position

import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.entity.EntityCollectionAPI
import latibro.automation.core.api.world.WorldAPI
import latibro.automation.core.context.position.PositionContext

final class PositionAPIImpl implements PositionAPI {

    private final PositionContext context

    PositionAPIImpl(PositionContext context) {
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
    EntityCollectionAPI getEntities() {
        return (EntityCollectionAPI) APIRegistry.getContextAPI(context.entityCollectionContext)
    }

}
