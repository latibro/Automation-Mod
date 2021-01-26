package latibro.automation.core.context.world

import latibro.automation.core.api.world.WorldAPI
import latibro.automation.core.api.world.WorldAPIImpl
import latibro.automation.core.context.position.CoordinatePositionContext
import latibro.automation.core.context.position.PositionContext

abstract class AbstractWorldContext implements WorldContext {

    WorldAPI getAPI() {
        return new WorldAPIImpl(this)
    }

    @Override
    PositionContext getPositionContextByCoordinate(double x, double y, double z) {
        return new CoordinatePositionContext(x, y, z, this)
    }

}