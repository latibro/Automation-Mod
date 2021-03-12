package latibro.automation.api.link

import latibro.automation.api.core.lua.LuaMethod

interface MultiLinkAPI<T extends LinkAPI> extends LinkAPI {

    @LuaMethod
    Number count()

    @LuaMethod
    T first()

    @LuaMethod
    List<T> asList()

    @LuaMethod
    List<T> asList(Number maxCount)

}