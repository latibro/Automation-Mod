package latibro.automation.core.api.world

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.api.API
import latibro.automation.core.api.entity.EntityGroupAPI
import latibro.automation.core.api.location.LocationAPI
import latibro.automation.core.api.server.ServerAPI

interface WorldAPI extends API {

    @LuaMethod
    ServerAPI getServer()

    @LuaMethod
    EntityGroupAPI getLoadedEntities()

    @LuaMethod
    LocationAPI getLocationByCoordinate(Number x, Number y, Number z)

}
