package latibro.automation.integration.immersiverailroading.api.rollingstock

import cam72cam.immersiverailroading.entity.LocomotiveDiesel
import latibro.automation.integration.rail.api.vehicle.traction.engine.DieselEngineControl

class IRDieselLocomotiveControl extends IRLocomotiveControl implements DieselEngineControl {

    IRDieselLocomotiveControl(IRRollingStock rollingStock) {
        super(rollingStock)
    }

    protected LocomotiveDiesel getIRLocomotiveDiesel() {
        return (LocomotiveDiesel) getIRLocomotive()
    }

    @Override
    void turnOn() {
        setTurnedOn(true)
    }

    @Override
    void turnOff() {
        setTurnedOn(false)
    }

    @Override
    void setTurnedOn(boolean state) {
        getIRLocomotiveDiesel().setTurnedOn(state)
    }

    @Override
    boolean isTurnedOn() {
        getIRLocomotiveDiesel().isTurnedOn()
    }

}
