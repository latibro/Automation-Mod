package latibro.automation.integration.immersiverailroading.api.rollingstock

import cam72cam.immersiverailroading.entity.Locomotive
import latibro.automation.core.context.entity.EntityContext

class LocomotiveAPIImpl<E extends Locomotive> extends RollingStockAPIImpl<E> implements LocomotiveAPI {

    LocomotiveAPIImpl(EntityContext context) {
        super(context)
    }

    @Override
    void setAirBrakeLevel(double level) {
        assert level >= 0
        assert level <= 1
        context.immersiveRailroadingRollingStock.setAirBrake(level as float)
    }

    @Override
    double getAirBrakeLevel() {
        return context.immersiveRailroadingRollingStock.getAirBrake()
    }

    @Override
    void setThrottleLevel(double level) {
        assert level >= -1
        assert level <= 1
        context.immersiveRailroadingRollingStock.setThrottle(level as float)
    }

    @Override
    double getThrottleLevel() {
        return context.immersiveRailroadingRollingStock.getThrottle()
    }

    double getCurrentSpeedInKMH() {
        return context.immersiveRailroadingRollingStock.getCurrentSpeed().metric()
    }

}
