package latibro.automation.integration.immersiverailroading.api.rollingstock


import cam72cam.immersiverailroading.entity.Locomotive
import cam72cam.immersiverailroading.library.KeyTypes
import latibro.automation.integration.immersiverailroading.context.RollingStockLinkContext

class LocomotiveAPIImpl extends RollingStockAPIImpl implements LocomotiveAPI {

    LocomotiveAPIImpl(RollingStockLinkContext context) {
        super(context)
    }

    RollingStockLinkContext<Locomotive> getContext() {
        return super.context as RollingStockLinkContext<Locomotive>
    }

    @Override
    void setAirBrakeLevel(Number level) {
        assert level >= 0
        assert level <= 1
        context.immersiveRailroadingRollingStock.setAirBrake(level as float)
    }

    @Override
    Number getAirBrakeLevel() {
        return context.immersiveRailroadingRollingStock.getAirBrake()
    }

    @Override
    void setThrottleLevel(Number level) {
        assert level >= -1
        assert level <= 1
        context.immersiveRailroadingRollingStock.setThrottle(level as float)
    }

    @Override
    Number getThrottleLevel() {
        return context.immersiveRailroadingRollingStock.getThrottle()
    }

    Number getCurrentSpeed() {
        return context.immersiveRailroadingRollingStock.getCurrentSpeed().metric()
    }

    @Override
    void soundHorn() {
        context.immersiveRailroadingRollingStock.setHorn(10, null)
    }

    @Override
    void startBell() {
        context.immersiveRailroadingRollingStock.handleKeyPress(null, KeyTypes.BELL)
    }

    @Override
    void stopBell() {
        context.immersiveRailroadingRollingStock.setBell(0)
    }

}
