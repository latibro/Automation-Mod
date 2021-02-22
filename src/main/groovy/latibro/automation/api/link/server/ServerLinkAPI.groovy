package latibro.automation.api.link.server

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.api.link.SingleLinkAPI
import latibro.automation.api.link.entity.EntityMultiLinkAPI

interface ServerLinkAPI extends SingleLinkAPI {

    @LuaMethod
    EntityMultiLinkAPI getLoadedEntities()

}