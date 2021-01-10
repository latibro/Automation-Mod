package latibro.automation.integration.immersiverailroading.api.rollingstock

import latibro.automation.integration.minecraft.api.entity.DirectEntity
import latibro.automation.integration.rail.api.vehicle.traction.engine.DieselEngineVehicle

class IRDieselLocomotive extends IRLocomotive implements DieselEngineVehicle {

    IRDieselLocomotive(DirectEntity entity) {
        super(entity)
    }

    @Override
    IRDieselLocomotiveControl getControl() {
        return new IRDieselLocomotiveControl(this)
    }

}
