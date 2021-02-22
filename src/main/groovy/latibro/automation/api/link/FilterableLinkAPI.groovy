package latibro.automation.api.link

import latibro.automation.api.core.lua.LuaMethod

interface FilterableLinkAPI<T extends LinkAPI> extends LinkAPI {

    @LuaMethod
    T filterByProperty(String property, Object value)

}