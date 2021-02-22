package latibro.automation.api.link

import latibro.automation.api.API
import latibro.automation.api.core.lua.LuaMethod

interface LinkAPI extends API {

    @LuaMethod
    Boolean isLinked()

    // static, dynamic, snapshot
    @LuaMethod
    String getLinkType()

}