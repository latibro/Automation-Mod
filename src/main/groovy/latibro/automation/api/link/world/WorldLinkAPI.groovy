package latibro.automation.api.link.world

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.api.link.SingleLinkAPI
import latibro.automation.api.link.UnloadableLinkAPI
import latibro.automation.api.link.entity.EntityMultiLinkAPI
import latibro.automation.api.link.location.LocationLinkAPI
import latibro.automation.api.link.server.ServerLinkAPI

interface WorldLinkAPI extends SingleLinkAPI, UnloadableLinkAPI {

    @LuaMethod
    ServerLinkAPI getServer()

    @LuaMethod
    EntityMultiLinkAPI getLoadedEntities()

    @LuaMethod
    LocationLinkAPI getLocationByCoordinates(Number x, Number y, Number z)

}
