package latibro.automation.lua.api

import latibro.automation.core.context.Context

class LuaAPIRegistry {

    static final LuaAPIRegistry INSTANCE = new LuaAPIRegistry()

    private final List<LuaAPIProvider> providers = []

    void register(LuaAPIProvider provider) {
        providers.add(provider)
    }

    List<LuaAPI> findAPIs(String name, Context context) {
        def apis = providers.findResults {
            it.getLuaAPI(name, context)
        }.unique()
        return apis
    }

    LuaAPI getLuaAPI(String name, Context context) {
        def apis = findAPIs(name, context)
        if (!apis) {
            return null
        }
        if (apis.size() > 1) {
            throw new Exception("Multiple (" + apis.size() + ") APIs found: " + name)
        }
        return apis.first()
    }

}
