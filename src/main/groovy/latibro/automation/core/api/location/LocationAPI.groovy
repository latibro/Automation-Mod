package latibro.automation.core.api.location

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.api.entity.EntityGroupAPI
import latibro.automation.core.api.world.WorldAPI

interface LocationAPI extends ContextAPI {

    @LuaMethod
    boolean isLoaded()

    @LuaMethod
    Number getX()

    @LuaMethod
    Number getY()

    @LuaMethod
    Number getZ()

    @LuaMethod
    WorldAPI getWorld()

    @LuaMethod
    EntityGroupAPI getEntities()

    @LuaMethod
    Number getDistanceToCoordinate(Number x, Number y, Number z)

}