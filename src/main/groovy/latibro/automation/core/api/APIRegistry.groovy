package latibro.automation.core.api

import latibro.automation.api.API

class APIRegistry {

    private static def REGISTRY = [:]

    static void register(String name, Class<? extends API> api) {
        REGISTRY.put(name, api)
    }

}
