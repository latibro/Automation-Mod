package latibro.automation.integration.rail.api.vehicle.traction.engine

import latibro.automation.api.core.lua.LuaMethod

interface DieselEngineControl extends EngineControl {

    @LuaMethod
    void turnOn()

    @LuaMethod
    void turnOff()

    @LuaMethod
    void setTurnedOn(boolean state)

    @LuaMethod
    boolean isTurnedOn()

}