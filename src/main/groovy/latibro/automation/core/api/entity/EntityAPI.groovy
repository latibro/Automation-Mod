package latibro.automation.core.api.entity

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.api.FeatureAPI
import latibro.automation.core.api.position.PositionAPI

interface EntityAPI extends ContextAPI {

    @LuaMethod(
            name = "isLoaded",
            usage = "function() : boolean"
    )
    boolean isLoaded()

    @LuaMethod(
            name = "getUUID",
            usage = "function() : string"
    )
    String getUUID()

    @LuaMethod(
            name = "getPosition",
            usage = "function() : PositionAPI"
    )
    PositionAPI getPosition()

    @LuaMethod
    EntityAPI asType(String name)

    @LuaMethod
    FeatureAPI getAPI(String name)

}