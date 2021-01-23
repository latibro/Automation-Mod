package latibro.automation.integration.minecraft.api.entity

import latibro.automation.api.core.lua.LuaMethod

interface EntityCollectionAPI {

    @LuaMethod
    EntityAPI getByUUID(UUID uuid)

    @LuaMethod
    Collection<EntityAPI> getAll()

}