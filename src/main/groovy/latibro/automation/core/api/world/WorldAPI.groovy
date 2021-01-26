package latibro.automation.core.api.world

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.api.API
import latibro.automation.core.api.entity.EntityCollectionAPI
import latibro.automation.core.api.position.PositionAPI
import latibro.automation.core.api.server.ServerAPI

interface WorldAPI extends API {

    @LuaMethod
    ServerAPI getServer()

    @LuaMethod
    EntityCollectionAPI getLoadedEntityCollection()

    @LuaMethod
    PositionAPI getPositionByCoordinate(double x, double y, double z)

}
