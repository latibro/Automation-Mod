package latibro.automation.integration.minecraft.api.entity

import latibro.automation.api.core.lua.LuaMethod

interface EntityCollectionAPI {

    @LuaMethod(
            name = "getByUUID"
    )
    EntityAPI getByUUIDString(String uuid)

    @LuaMethod
    Collection<EntityAPI> getAll()

}