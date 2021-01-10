package latibro.automation.integration.minecraft.api.entity

import latibro.automation.api.core.lua.LuaMethod

interface EntityAPI {

    @LuaMethod(
            name = "linkByUUID",
            usage = "function(uuid: string) : LinkedEntity"
    )
    LinkedEntity getLinkedEntityFromUUID(UUID uuid)

    @LuaMethod(
            name = "getByUUID",
            usage = "function(uuid: string) : DirectEntity"
    )
    DirectEntity getDirectEntityByUUID(String uuid)

    DirectEntity getDirectEntityByUUID(UUID uuid)

}
