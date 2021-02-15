package latibro.automation.core.api

import latibro.automation.core.context.Context
import latibro.automation.integration.minecraft.MinecraftAPIProvider

final class APIRegistry {

    private static final List<APIProvider> providers = (List<APIProvider>) [new CoreAPIProvider(), new MinecraftAPIProvider()]

    static void register(APIProvider provider) {
        providers.add(provider)
    }

    static ContextAPI getAPI(Context context) {
        def result = providers.findResult {
            it.getAPI(context)
        }
        if (result) {
            return result
        }
        throw new NullPointerException()
    }

    static List<String> getAPINames(API api) {
        return providers.findResult {
            return it.getAPINames(api)
        }?.flatten()?: [] as List<String>
    }

    static boolean hasAPI(String name, API api) {
        return providers.any {
            return it.hasAPI(name, api)
        }
    }

    static API getAPI(String name, API api) {
        def result = providers.findResult {
            return it.getAPI(name, api)
        }
        if (result) {
            return result
        }
        throw new NullPointerException()
    }

}
