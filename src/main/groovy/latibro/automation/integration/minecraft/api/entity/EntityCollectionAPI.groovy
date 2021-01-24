package latibro.automation.integration.minecraft.api.entity

import latibro.automation.api.core.lua.LuaMethod

interface EntityCollectionAPI {

    @LuaMethod(
            name = "getByUUID"
    )
    EntityAPI getByUUIDAsString(String uuid)

    @LuaMethod(
            name = "getAll"
    )
    Collection<EntityAPI> getAll()

    @LuaMethod(
            name = "getAllAsUUID"
    )
    Collection<String> getAllAsUUIDAsString()

    @LuaMethod(
            name = "size"
    )
    int size()

    @LuaMethod(
            name = "getAt"
    )
    EntityAPI getAt(int index)

}