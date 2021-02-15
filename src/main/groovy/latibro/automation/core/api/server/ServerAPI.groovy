package latibro.automation.core.api.server

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.api.API
import latibro.automation.core.api.entity.EntityGroupAPI

interface ServerAPI extends API {

    @LuaMethod
    EntityGroupAPI getLoadedEntities()

}