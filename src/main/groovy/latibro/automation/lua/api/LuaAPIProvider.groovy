package latibro.automation.lua.api


import latibro.automation.core.context.Context

interface LuaAPIProvider {

    LuaAPI getLuaAPI(String name, Context context)

}