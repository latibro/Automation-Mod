package latibro.automation.integration.minecraft.api.position

import latibro.automation.core.context.position.PositionContext
import latibro.automation.integration.minecraft.api.world.WorldAPI
import latibro.automation.integration.minecraft.api.world.WorldAPIImpl

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
        return new WorldAPIImpl(context.worldContext)
    }

}
