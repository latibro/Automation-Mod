package latibro.automation.integration.rail.api.vehicle.traction

import latibro.automation.api.core.lua.LuaMethod

interface TractionControl {

    @LuaMethod
    void setThrottleLevel(double level)

    @LuaMethod
    double getThrottleLevel()

}