package latibro.automation.core.api.server

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.api.entity.EntityGroupAPI

interface ServerAPI extends ContextAPI {

    @LuaMethod
    EntityGroupAPI getLoadedEntities()

}