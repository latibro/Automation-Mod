package latibro.automation.api

import latibro.automation.api.core.lua.LuaMethod

interface APIProviderAPI {

    @LuaMethod
    List<String> getAPINames()

    @LuaMethod
    Boolean hasAPI(String name)

    @LuaMethod
    API getAPI(String name)

}