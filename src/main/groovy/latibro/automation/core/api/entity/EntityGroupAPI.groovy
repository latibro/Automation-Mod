package latibro.automation.core.api.entity

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.api.ContextAPI

interface EntityGroupAPI extends ContextAPI {

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

    // "and" conditions
    //@LuaMethod //TODO enable this lua method when implemented
    EntityGroupAPI where(Map conditions)

    // "or" conditions
    //@LuaMethod //TODO enable this lua methods when implemented
    EntityGroupAPI whereAny(Map conditions)

    @LuaMethod
    List<Object> getAllAsProperty(String property)

}