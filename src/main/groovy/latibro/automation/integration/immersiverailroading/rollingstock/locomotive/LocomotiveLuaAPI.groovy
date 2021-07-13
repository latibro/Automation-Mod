package latibro.automation.integration.immersiverailroading.rollingstock.locomotive

import cam72cam.immersiverailroading.entity.Locomotive
import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.context.Context
import latibro.automation.integration.immersiverailroading.rollingstock.RollingStockLuaAPI
import latibro.automation.lua.annotation.LuaFunction
import latibro.automation.lua.annotation.LuaObject

@LuaObject
class LocomotiveLuaAPI extends RollingStockLuaAPI {

    LocomotiveLuaAPI(Context context) {
        super(context)
        assert(locomotive)
    }

    private Locomotive getLocomotive() {
        return context.getSource(Locomotive)
    }

    @LuaMethod //TODO should not have LuaMethod annotation
    @LuaFunction
    Number getCurrentSpeed() {
        return locomotive.getCurrentSpeed().metric()
    }

    @LuaMethod //TODO should not have LuaMethod annotation
    @LuaFunction
    void setThrottleLevel(Number level) {
        assert(level >= -1)
        assert(level <= 1)
        locomotive.setThrottle(level as float)
    }

    @LuaMethod //TODO should not have LuaMethod annotation
    @LuaFunction
    Number getThrottleLevel() {
        return locomotive.getThrottle()
    }

    @LuaMethod //TODO should not have LuaMethod annotation
    @LuaFunction
    void setAirBrakeLevel(Number level) {
        assert(level >= 0)
        assert(level <= 1)
        locomotive.setAirBrake(level as float)
    }

    @LuaMethod //TODO should not have LuaMethod annotation
    @LuaFunction
    Number getAirBrakeLevel() {
        return locomotive.getAirBrake()
    }

    @LuaMethod //TODO should not have LuaMethod annotation
    @LuaFunction
    void soundHorn() {
        // Sounds horn for 40 ticks
        locomotive.setHorn(40, null)
    }

    @LuaMethod //TODO should not have LuaMethod annotation
    @LuaFunction
    void soundBell() {
        // Sounds bell for 40 ticks, or toggle bell if loco is configured to bell toggle
        locomotive.setBell(40)
    }

    @LuaMethod //TODO should not have LuaMethod annotation
    @LuaFunction
    void stopBell() {
        locomotive.setBell(0)
    }

}
