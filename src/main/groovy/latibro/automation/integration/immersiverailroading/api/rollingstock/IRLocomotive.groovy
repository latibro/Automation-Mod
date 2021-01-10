package latibro.automation.integration.immersiverailroading.api.rollingstock

import latibro.automation.integration.minecraft.api.entity.DirectEntity
import latibro.automation.integration.rail.api.vehicle.brake.BrakeableVehicle
import latibro.automation.integration.rail.api.vehicle.traction.engine.EngineVehicle

class IRLocomotive extends IRRollingStock implements BrakeableVehicle, EngineVehicle {

    IRLocomotive(DirectEntity entity) {
        super(entity)
    }

    @Override
    IRLocomotiveControl getControl() {
        return new IRLocomotiveControl(this)
    }

}
