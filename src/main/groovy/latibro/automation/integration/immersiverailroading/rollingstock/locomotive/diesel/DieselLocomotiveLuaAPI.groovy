package latibro.automation.integration.immersiverailroading.rollingstock.locomotive.diesel

import cam72cam.immersiverailroading.entity.LocomotiveDiesel
import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.context.Context
import latibro.automation.integration.immersiverailroading.rollingstock.locomotive.LocomotiveLuaAPI
import latibro.automation.lua.annotation.LuaFunction
import latibro.automation.lua.annotation.LuaObject

@LuaObject
class DieselLocomotiveLuaAPI extends LocomotiveLuaAPI {

    DieselLocomotiveLuaAPI(Context context) {
        super(context)
        assert(dieselLocomotive)
    }

    private LocomotiveDiesel getDieselLocomotive() {
        return context.getSource(LocomotiveDiesel)
    }

    @LuaMethod //TODO should not have LuaMethod annotation
    @LuaFunction
    void startEngine() {
        dieselLocomotive.setTurnedOn(true)
    }

    @LuaMethod //TODO should not have LuaMethod annotation
    @LuaFunction
    void stopEngine() {
        dieselLocomotive.setTurnedOn(false)
    }

    @LuaMethod //TODO should not have LuaMethod annotation
    @LuaFunction
    Boolean isEngineRunning() {
        dieselLocomotive.isTurnedOn()
    }

}