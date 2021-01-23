package latibro.automation.integration.minecraft.api.server

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.api.API
import latibro.automation.integration.minecraft.api.entity.EntityCollectionAPI

interface ServerAPI extends API {

    @LuaMethod
    EntityCollectionAPI getAllLoadedEntity()

}