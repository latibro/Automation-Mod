package latibro.automation.core.api

import latibro.automation.api.API
import latibro.automation.core.context.Context

final class APIRegistry {

    private static final List<APIProvider> providers = (List<APIProvider>) [new CoreAPIProvider()]
    private static final APIProvider fallbackProvider = new BaseAPIProvider()

    static void register(APIProvider provider) {
        providers.add(provider)
    }

    static API getAPI(Context context) {
        def api = providers.findResult {
            it.getAPI(context)
        }
        if (api) {
            return api
        }
        return fallbackProvider.getAPI(context)
    }

    static List<String> getAPINames(API api) {
        return (providers.findResults {
            return it.getAPINames(api)
        }?.flatten() ?: []) as List<String>
    }

    static boolean hasAPI(String name, API api) {
        return providers.any {
            return it.hasAPI(name, api)
        }
    }

    static API getAPI(String name, API api) {
        return providers.findResult {
            return it.getAPI(name, api)
        }
    }

}
