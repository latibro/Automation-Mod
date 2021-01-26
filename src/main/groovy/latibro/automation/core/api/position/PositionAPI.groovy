package latibro.automation.core.api.position

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.api.API
import latibro.automation.core.api.world.WorldAPI

interface PositionAPI extends API {

    @LuaMethod
    double getX()

    @LuaMethod
    double getY()

    @LuaMethod
    double getZ()

    WorldAPI getWorld()

}