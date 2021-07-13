package latibro.automation.integration.immersiverailroading


import latibro.automation.core.context.Context
import latibro.automation.integration.immersiverailroading.rollingstock.RollingStockLuaAPI
import latibro.automation.integration.immersiverailroading.rollingstock.locomotive.LocomotiveLuaAPI
import latibro.automation.integration.immersiverailroading.rollingstock.locomotive.diesel.DieselLocomotiveLuaAPI
import latibro.automation.lua.api.LuaAPI
import latibro.automation.lua.api.LuaAPIProvider

class ImmersiveRailroadingLuaAPIProvider implements LuaAPIProvider {

    @Override
    LuaAPI getLuaAPI(String name, Context context) {
        if (name == "immersive-railroading:rolling-stock") {
            return new RollingStockLuaAPI(context)
        }

        if (name == "immersive-railroading:locomotive") {
            return new LocomotiveLuaAPI(context)
        }

        if (name == "immersive-railroading:diesel-locomotive") {
            return new DieselLocomotiveLuaAPI(context)
        }

        return null
    }

}
