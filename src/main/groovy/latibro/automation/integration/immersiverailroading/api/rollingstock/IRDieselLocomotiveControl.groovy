package latibro.automation.integration.immersiverailroading.api.rollingstock

import cam72cam.immersiverailroading.entity.LocomotiveDiesel
import latibro.automation.integration.rail.api.vehicle.traction.engine.DieselEngineControl

class IRDieselLocomotiveControl extends IRLocomotiveControl implements DieselEngineControl {

    IRDieselLocomotiveControl(IRDieselLocomotive rollingStock) {
        super(rollingStock)
    }

    protected LocomotiveDiesel getIREntity() {
        return (LocomotiveDiesel) super.getIREntity()
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
        getIREntity().setTurnedOn(state)
    }

    @Override
    boolean isTurnedOn() {
        getIREntity().isTurnedOn()
    }

}
