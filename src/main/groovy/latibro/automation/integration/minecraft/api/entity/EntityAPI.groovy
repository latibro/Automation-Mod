package latibro.automation.integration.minecraft.api.entity

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.api.API

interface EntityAPI extends API {

    @LuaMethod(
            name = "getAllLoadedAsUUID",
            usage = "function() : array<uuid : string>"
    )
    List<String> getAllLoadedAsUUIDString()

    @LuaMethod(
            name = "getAllLoaded",
            usage = "function() : array<entity : userdata<Entity>>"
    )
    List<Entity> getAllLoaded()

    @LuaMethod(
            name = "getByUUID",
            usage = "function(uuid: string) : userdata<Entity>"
    )
    Entity getByUUIDString(String uuid)

    Entity getByUUID(UUID uuid)

}
