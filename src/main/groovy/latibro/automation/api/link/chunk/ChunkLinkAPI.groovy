package latibro.automation.api.link.chunk

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.api.link.SingleLinkAPI
import latibro.automation.api.link.world.WorldLinkAPI

interface ChunkLinkAPI extends SingleLinkAPI {

    @LuaMethod
    Boolean isLoaded()

    @LuaMethod
    WorldLinkAPI getWorld()

    @LuaMethod
    Boolean isForceLoaded()

    @LuaMethod
    void forceLoad()

    @LuaMethod
    void unforceLoad()

}