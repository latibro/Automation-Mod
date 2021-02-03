package latibro.automation.integration.immersiverailroading.api.rollingstock

import latibro.automation.api.core.lua.LuaMethod

interface DieselLocomotiveAPI extends LocomotiveAPI {

    @LuaMethod
    void startEngine()

    @LuaMethod
    void stopEngine()

    @LuaMethod
    boolean isEngineRunning()

}