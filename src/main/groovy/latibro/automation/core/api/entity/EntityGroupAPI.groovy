package latibro.automation.core.api.entity

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.api.API

interface EntityGroupAPI extends API {

    @LuaMethod
    Number size()

    @LuaMethod
    EntityAPI get(Number index)

    @LuaMethod
    EntityAPI first()

    @LuaMethod
    EntityAPI last()

    @LuaMethod
    List<EntityAPI> getAll()

    @LuaMethod
    EntityAPI findBy(String property, Object expected)

    @LuaMethod
    List<EntityAPI> findAllBy(String property, Object expected)

    @LuaMethod
    EntityGroupAPI where(String property, Object expected)

    @LuaMethod
    List<Object> getAllAsProperty(String property)

}