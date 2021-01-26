package latibro.automation.core.api.server

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.api.API
import latibro.automation.core.api.entity.EntityCollectionAPI

interface ServerAPI extends API {

    @LuaMethod
    EntityCollectionAPI getLoadedEntityCollection()

}