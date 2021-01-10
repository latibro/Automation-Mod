package latibro.automation.integration.immersiverailroading.api.rollingstock

import cam72cam.immersiverailroading.entity.Locomotive
import latibro.automation.integration.rail.api.vehicle.brake.BrakeControl
import latibro.automation.integration.rail.api.vehicle.traction.engine.EngineControl

class IRLocomotiveControl extends IRRollingStockControl implements BrakeControl, EngineControl {

    IRLocomotiveControl(IRLocomotive rollingStock) {
        super(rollingStock)
    }

    protected Locomotive getIREntity() {
        return (Locomotive) super.getIREntity()
    }

    @Override
    void setBrakeLevel(double level) {
        assert level >= 0
        assert level <= 100
        getIREntity().setAirBrake((level / 100d) as float)
    }

    @Override
    double getBrakeLevel() {
        return getIREntity().getAirBrake() * 100d
    }


    @Override
    void setThrottleLevel(double level) {
        assert level >= -100
        assert level <= 100
        getIREntity().setThrottle((level / 100d) as float)
    }

    @Override
    double getThrottleLevel() {
        return getIREntity().getThrottle() * 100d
    }

}
