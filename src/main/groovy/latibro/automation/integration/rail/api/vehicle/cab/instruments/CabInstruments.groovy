package latibro.automation.integration.rail.api.vehicle.cab.instruments

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.integration.rail.api.vehicle.cab.Cab

interface CabInstruments {

    @LuaMethod
    Cab getCab()

    @LuaMethod
    double getSpeed()

    @LuaMethod
    double getFuelLevel()

}