package latibro.automation.integration.rail.api.vehicle.cab

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.integration.rail.api.vehicle.cab.Cab

interface CabVehicle {

    @LuaMethod
    Cab getCab()

}