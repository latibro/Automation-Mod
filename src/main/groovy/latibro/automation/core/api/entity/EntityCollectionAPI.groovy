package latibro.automation.core.api.entity

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.api.ContextAPI

interface EntityCollectionAPI extends ContextAPI {

    @LuaMethod(
            name = "getByUUID"
    )
    EntityAPI getByUUID(String uuid)

    @LuaMethod(
            name = "getAll"
    )
    Collection<EntityAPI> getAll()

    @LuaMethod(
            name = "getAllAsUUID"
    )
    Collection<String> getAllAsUUID()

    @LuaMethod(
            name = "size"
    )
    double size()

    @LuaMethod(
            name = "getAt"
    )
    EntityAPI getAt(double index)

}