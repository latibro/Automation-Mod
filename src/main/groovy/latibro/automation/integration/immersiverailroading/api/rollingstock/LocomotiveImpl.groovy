package latibro.automation.integration.immersiverailroading.api.rollingstock

import latibro.automation.integration.minecraft.api.entity.Entity

class LocomotiveImpl<E extends cam72cam.immersiverailroading.entity.Locomotive> extends RollingStockImpl<E> implements Locomotive {

    protected LocomotiveImpl(Entity entity) {
        super(entity)
    }

    @Override
    void setAirBrakeLevel(double level) {
        assert level >= 0
        assert level <= 1
        getIREntity().setAirBrake(level as float)
    }

    @Override
    double getAirBrakeLevel() {
        return getIREntity().getAirBrake()
    }

    @Override
    void setThrottleLevel(double level) {
        assert level >= -1
        assert level <= 1
        getIREntity().setThrottle(level as float)
    }

    @Override
    double getThrottleLevel() {
        return getIREntity().getThrottle()
    }

    double getCurrentSpeedInKMH() {
        return getIREntity().getCurrentSpeed().metric()
    }

}
