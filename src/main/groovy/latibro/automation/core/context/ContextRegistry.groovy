package latibro.automation.core.context

import latibro.automation.core.source.SourceProvider

final class ContextRegistry {

    private static final List<SourceProvider> objectProviders = (List<SourceProvider>) []

    static void register(SourceProvider provider) {
        objectProviders.add(provider)
    }

    static <T> T getContextObject(Class<T> type, Context context) {
        def objects = objectProviders.findResults {
            it.getSource(type, context)
        }
        if (!objects) {
            throw new NullPointerException("Context object not found: " + type)
        }
        if (objects?.size() == 1) {
            return objects.first()
        }
        throw new Exception("Multiple (" + objects.size() + ") context objects found: " + type)
    }

}
