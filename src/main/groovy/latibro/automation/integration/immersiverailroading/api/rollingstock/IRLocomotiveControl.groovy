package latibro.automation.integration.immersiverailroading.api.rollingstock

import cam72cam.immersiverailroading.entity.Locomotive
import latibro.automation.integration.rail.api.vehicle.brake.BrakeControl
import latibro.automation.integration.rail.api.vehicle.traction.engine.EngineControl

class IRLocomotiveControl extends IRRollingStockControl implements BrakeControl, EngineControl {

    IRLocomotiveControl(IRRollingStock rollingStock) {
        super(rollingStock)
    }

    protected Locomotive getIRLocomotive() {
        return (Locomotive) rollingStock
    }

    @Override
    void setBrakeLevel(double level) {
        assert level >= 0
        assert level <= 100
        getIRLocomotive().setAirBrake((level / 100d) as float)
    }

    @Override
    double getBrakeLevel() {
        return getIRLocomotive().getAirBrake() * 100d
    }


    @Override
    void setThrottleLevel(double level) {
        assert level >= -100
        assert level <= 100
        getIRLocomotive().setThrottle((level / 100d) as float)
    }

    @Override
    double getThrottleLevel() {
        return getIRLocomotive().getThrottle() * 100d
    }

}
