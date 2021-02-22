package latibro.automation.api.link

import latibro.automation.api.core.lua.LuaMethod

interface MultiLinkAPI<T extends LinkAPI> extends LinkAPI {

    @LuaMethod
    Number count()

    @LuaMethod
    List<T> asList()

}