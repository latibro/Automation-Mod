package latibro.automation.core.context.position

import latibro.automation.core.context.CoreContext
import latibro.automation.core.context.world.WorldContext

final class CoordinatePositionContext implements CoreContext, PositionContext {

    private final double x
    private final double y
    private final double z
    private final WorldContext world

    CoordinatePositionContext(double x, double y, double z, WorldContext world) {
        this.x = Objects.requireNonNull(x)
        this.y = Objects.requireNonNull(y)
        this.z = Objects.requireNonNull(z)
        this.world = Objects.requireNonNull(world)
    }

    @Override
    double getX() {
        return x
    }

    @Override
    double getY() {
        return y
    }

    @Override
    double getZ() {
        return z
    }

    @Override
    WorldContext getWorldContext() {
        return world
    }

}
