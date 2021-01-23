package latibro.automation.integration.minecraft.api.world

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.api.API
import latibro.automation.integration.minecraft.api.entity.EntityCollectionAPI
import latibro.automation.integration.minecraft.api.position.PositionAPI
import latibro.automation.integration.minecraft.api.server.ServerAPI

interface WorldAPI extends API {

    @LuaMethod
    ServerAPI getServer()

    @LuaMethod
    EntityCollectionAPI getAllLoadedEntity()

    @LuaMethod
    PositionAPI getPositionByXYZ(double x, double y, double z)

}
