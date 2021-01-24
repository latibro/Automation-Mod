package latibro.automation.integration.minecraft.api.entity

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.integration.minecraft.api.position.PositionAPI

interface EntityAPI {

    @LuaMethod(
            name = "isLoaded",
            usage = "function() : boolean"
    )
    boolean isLoaded()

    @LuaMethod(
            name = "getUUID",
            usage = "function() : string"
    )
    String getUUIDAsString()

    @LuaMethod(
            name = "getType",
            usage = "function() : string"
    )
    String getTypeAsString()

    @LuaMethod(
            name = "getPosition",
            usage = "function() : PositionAPI"
    )
    PositionAPI getPosition()

    @LuaMethod(
            name = "getFacing",
            usage = "function() : string"
    )
    String getFacingAsString()

    @LuaMethod
    EntityAPI asAPI(String name)

}