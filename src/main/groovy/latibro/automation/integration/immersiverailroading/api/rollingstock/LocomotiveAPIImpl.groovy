package latibro.automation.integration.immersiverailroading.api.rollingstock


import cam72cam.immersiverailroading.entity.Locomotive
import latibro.automation.integration.immersiverailroading.context.RollingStockContext

class LocomotiveAPIImpl extends RollingStockAPIImpl implements LocomotiveAPI {

    LocomotiveAPIImpl(RollingStockContext context) {
        super(context)
    }

    protected RollingStockContext<Locomotive> getContext() {
        return super.context as RollingStockContext<Locomotive>
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
