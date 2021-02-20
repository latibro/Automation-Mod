package latibro.automation.core.api.location

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.api.API
import latibro.automation.core.api.entity.EntityGroupAPI
import latibro.automation.core.api.world.WorldAPI

interface LocationAPI extends API {

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
    Number getDistanceToCoordinates(Number x, Number y, Number z)

}