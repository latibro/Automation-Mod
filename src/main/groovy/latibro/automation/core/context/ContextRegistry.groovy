package latibro.automation.core.context

import latibro.automation.integration.minecraft.MinecraftContextProvider

final class ContextRegistry {

    private static final List<ContextProvider> providers = (List<ContextProvider>) [new CoreContextProvider(), new MinecraftContextProvider()]

    static void register(ContextProvider provider) {
        providers.add(provider)
    }

    static boolean hasContext(Class<? extends Context> cls, Context context) {
        return providers.any {
            return it.hasContext(cls, context)
        }
    }

    static Context getContext(Class<? extends Context> cls, Context context) {
        def result = providers.findResult {
            return it.getContext(cls, context)
        }
        if (result) {
            return result
        }
        throw new NullPointerException()
    }

}
