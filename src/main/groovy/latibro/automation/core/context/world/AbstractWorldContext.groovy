package latibro.automation.core.context.world


import latibro.automation.core.context.position.CoordinatePositionContext
import latibro.automation.core.context.position.PositionContext

abstract class AbstractWorldContext implements WorldContext {

    @Override
    PositionContext getPositionContextByCoordinate(double x, double y, double z) {
        return new CoordinatePositionContext(x, y, z, this)
    }

}