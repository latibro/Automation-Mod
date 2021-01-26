package latibro.automation.core.api.position

import latibro.automation.core.api.world.WorldAPI
import latibro.automation.core.context.position.PositionContext

class PositionAPIImpl implements PositionAPI {

    private final PositionContext context

    PositionAPIImpl(PositionContext context) {
        this.context = Objects.requireNonNull(context)
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
        return context.worldContext.getAPI()
    }

}
