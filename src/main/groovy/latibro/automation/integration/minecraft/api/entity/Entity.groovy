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
            usage = "function() : string"
    )
    String getType()

    @LuaMethod(
            usage = "function() : Position"
    )
    //@LuaMethodReturn(transformer = Table(["x":"getX" ,"y":getY","z":"getZ"]))
    Position getPosition()

    @LuaMethod(
            usage = "function() : boolean"
    )
    boolean isAvailable()

    //TODO direction / facing

}