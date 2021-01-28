package latibro.automation.core.api.server

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.api.entity.EntityCollectionAPI

interface ServerAPI extends ContextAPI {

    @LuaMethod
    EntityCollectionAPI getLoadedEntityCollection()

}