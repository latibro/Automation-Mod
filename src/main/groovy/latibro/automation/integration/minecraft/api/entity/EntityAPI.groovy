package latibro.automation.integration.minecraft.api.entity

import latibro.automation.api.core.lua.LuaMethod

interface EntityAPI {

    @LuaMethod(
            name = "getAllLoadedAsUUID",
            usage = "function() : array<uuid : string>"
    )
    List<String> getAllLoadedAsUUIDString()

    List<UUID> getAllLoadedAsUUID()

    @LuaMethod(
            name = "getAllLoaded",
            usage = "function() : array<entity : userdata<Entity>>"
    )
    List<DirectEntity> getAllLoaded()

    @LuaMethod(
            name = "getByUUID",
            usage = "function(uuid: string) : userdata<Entity>"
    )
    DirectEntity getByUUIDString(String uuid)

    DirectEntity getByUUID(UUID uuid)

    LinkedEntity linkByUUID(UUID uuid)

}
