package latibro.automation.core.context

import latibro.automation.integration.minecraft.MinecraftContextProvider

final class ContextRegistry {

    private static final List<ContextProvider> providers = (List<ContextProvider>) [new CoreContextProvider(), new MinecraftContextProvider()]

    static void add(ContextProvider provider) {
        providers.add(provider)
    }

    static List<String> getSubContextNames(Context context) {
        return (List<String>) providers.findResult {
            return it.getSubContextNames(context)
        }?.flatten()
    }

    static Context getSubContext(String name, Context context) {
        //TODO add some caching
        def api = providers.findResult {
            return it.findSubContext(name, context)
        }
        if (api) {
            return api
        }
        throw new NullPointerException()
    }

}
