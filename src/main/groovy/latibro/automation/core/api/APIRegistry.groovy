package latibro.automation.core.api

import latibro.automation.core.context.Context
import latibro.automation.integration.minecraft.MinecraftAPIProvider

final class APIRegistry {

    private static final List<APIProvider> providers = (List<APIProvider>) [new CoreAPIProvider(), new MinecraftAPIProvider()]

    static void add(APIProvider provider) {
        providers.add(provider)
    }

    static ContextAPI getContextAPI(Context context) {
        //TODO add some caching
        def api = providers.findResult {
            return it.findContextAPI(context)
        }
        if (api) {
            return api
        }
        throw new NullPointerException()
    }

    static List<String> getFeatureAPINames(Context context) {
        return (List<String>) providers.findResult {
            return it.getFeatureAPINames(context)
        }?.flatten()
    }

    static FeatureAPI getFeatureAPI(String name, Context context) {
        //TODO add some caching
        def api = providers.findResult {
            return it.findFeatureAPI(name, context)
        }
        if (api) {
            return api
        }
        throw new NullPointerException()
    }

}
