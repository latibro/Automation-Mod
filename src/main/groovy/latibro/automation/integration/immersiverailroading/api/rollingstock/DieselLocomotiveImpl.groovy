package latibro.automation.integration.immersiverailroading.api.rollingstock

import cam72cam.immersiverailroading.entity.LocomotiveDiesel
import latibro.automation.integration.minecraft.api.entity.Entity

class DieselLocomotiveImpl extends LocomotiveImpl<LocomotiveDiesel> implements DieselLocomotive {

    protected DieselLocomotiveImpl(Entity entity) {
        super(entity)
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
