package latibro.automation.integration.immersiverailroading.api.rollingstock

import latibro.automation.api.core.lua.LuaMethod

interface DieselLocomotiveAPI extends LocomotiveAPI {

    @LuaMethod
    void turnOn()

    @LuaMethod
    void turnOff()

    @LuaMethod
    void setTurnedOn(boolean state)

    @LuaMethod
    boolean isTurnedOn()

}