package latibro.automation.core.api.world

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.api.entity.EntityCollectionAPI
import latibro.automation.core.api.position.PositionAPI
import latibro.automation.core.api.server.ServerAPI

interface WorldAPI extends ContextAPI {

    @LuaMethod
    ServerAPI getServer()

    @LuaMethod
    EntityCollectionAPI getLoadedEntityCollection()

    @LuaMethod
    PositionAPI getPositionByCoordinate(double x, double y, double z)

}
