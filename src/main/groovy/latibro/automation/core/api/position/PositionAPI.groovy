package latibro.automation.core.api.position

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.api.world.WorldAPI

interface PositionAPI extends ContextAPI {

    @LuaMethod
    double getX()

    @LuaMethod
    double getY()

    @LuaMethod
    double getZ()

    WorldAPI getWorld()

}