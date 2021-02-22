package latibro.automation.api.link

import latibro.automation.api.core.lua.LuaMethod

interface UnloadableLinkAPI extends LinkAPI {

    @LuaMethod
    Boolean isLoaded()

}