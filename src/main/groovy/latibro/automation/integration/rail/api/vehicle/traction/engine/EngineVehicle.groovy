package latibro.automation.integration.rail.api.vehicle.traction.engine

import latibro.automation.integration.rail.api.vehicle.traction.TractionVehicle

interface EngineVehicle extends TractionVehicle {

    EngineControl getControl()

}