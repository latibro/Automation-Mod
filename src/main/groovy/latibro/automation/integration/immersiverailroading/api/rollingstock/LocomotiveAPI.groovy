package latibro.automation.integration.immersiverailroading.api.rollingstock

import latibro.automation.api.core.lua.LuaMethod

interface LocomotiveAPI extends RollingStockAPI {

    @LuaMethod
    void setAirBrakeLevel(Number level)

    @LuaMethod
    Number getAirBrakeLevel()

    @LuaMethod
    void setThrottleLevel(Number level)

    @LuaMethod
    Number getThrottleLevel()

    @LuaMethod
    double getCurrentSpeed()

    @LuaMethod
    void soundHorn()

    @LuaMethod
    void startBell()

    @LuaMethod
    void stopBell()

}