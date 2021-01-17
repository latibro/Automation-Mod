package latibro.automation.integration.minecraft.api.entity

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.integration.minecraft.api.position.Position

interface Entity {

    net.minecraft.entity.Entity getMinecraftEntity()

    @LuaMethod(
            name = "getUUID",
            usage = "function() : string"
    )
    //@LuaMethodReturn(transformer = String)
    String getUUIDString()

    UUID getUUID()

    @LuaMethod(
            name = "getType",
            usage = "function() : string"
    )
    String getType()

    @LuaMethod(
            name = "getPosition",
            usage = "function() : Position"
    )
    //@LuaMethodReturn(transformer = Table(["x":"getX" ,"y":getY","z":"getZ"]))
    Position getPosition()

    @LuaMethod(
            name = "getFacing",
            usage = "function() : string"
    )
    String getFacingAsString()

    @LuaMethod(
            name = "isAvailable",
            usage = "function() : boolean"
    )
    boolean isAvailable()

    //TODO direction / facing

}