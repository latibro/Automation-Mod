package latibro.automation.core.api.entity

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.api.API

interface EntityGroupAPI extends API {

    @LuaMethod
    Number size()

    @LuaMethod
    EntityGroupAPI whereProperty(String property, Object value)

    @LuaMethod
    List<EntityAPI> asList()

}