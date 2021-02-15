package latibro.automation.core.api.entity

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.api.API
import latibro.automation.core.api.location.LocationAPI

interface EntityAPI extends API {

    @LuaMethod
    boolean isLoaded()

    @LuaMethod
    String getUUID()

    @LuaMethod
    LocationAPI getLocation()

    @LuaMethod
    String getName()

    @LuaMethod
    String getType()

    @LuaMethod
    List<String> getAPINames()

    @LuaMethod
    boolean hasAPI(String name)

    @LuaMethod
    API getAPI(String name)

}