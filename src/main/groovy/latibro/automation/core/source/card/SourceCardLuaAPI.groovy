package latibro.automation.core.source.card

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.context.Context
import latibro.automation.lua.annotation.LuaFunction
import latibro.automation.lua.annotation.LuaObject
import latibro.automation.lua.api.LuaAPI

@LuaObject
class SourceCardLuaAPI implements LuaAPI {

    private final Context context

    SourceCardLuaAPI(Context context) {
        this.context = context
    }

    @LuaMethod //TODO should not have LuaMethod annotation
    @LuaFunction
    LuaAPI getApi(String apiName) {
        def api = context.getLuaAPI(apiName)
        if (api) {
            return api
        }
        throw new NullPointerException("API not found")
    }

}