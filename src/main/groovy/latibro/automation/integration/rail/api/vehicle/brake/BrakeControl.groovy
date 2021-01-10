package latibro.automation.integration.rail.api.vehicle.brake

import latibro.automation.api.core.lua.LuaMethod

interface BrakeControl {

    @LuaMethod
    void setBrakeLevel(double level)

    @LuaMethod
    double getBrakeLevel()

}