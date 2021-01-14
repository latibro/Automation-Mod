package latibro.automation.integration.immersiverailroading.api.rollingstock

import latibro.automation.api.core.lua.LuaMethod

interface DieselLocomotive extends Locomotive {

    @LuaMethod
    void turnOn()

    @LuaMethod
    void turnOff()

    @LuaMethod
    void setTurnedOn(boolean state)

    @LuaMethod
    boolean isTurnedOn()

}