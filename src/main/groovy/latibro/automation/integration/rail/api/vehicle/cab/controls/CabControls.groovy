package latibro.automation.integration.rail.api.vehicle.cab.controls

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.integration.rail.api.vehicle.cab.Cab

interface CabControls {

    @LuaMethod
    Cab getCab()

    @LuaMethod
    void setThrottleLevel(double level)

    @LuaMethod
    double getThrottleLevel()

    @LuaMethod
    void setBrakeLevel(double level)

    @LuaMethod
    double getBrakeLevel()

    @LuaMethod
    void setIgnitionState(boolean state)

    @LuaMethod
    boolean getIgnitionState()

    @LuaMethod
    void soundHorn()

}