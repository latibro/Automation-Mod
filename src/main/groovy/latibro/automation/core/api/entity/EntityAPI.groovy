package latibro.automation.core.api.entity

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.api.FeatureAPI
import latibro.automation.core.api.location.LocationAPI

interface EntityAPI extends ContextAPI {

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
    EntityAPI asType(String name)

    //@LuaMethod TODO re-enable lua method again when it has a use case
    FeatureAPI getAPI(String name)

}