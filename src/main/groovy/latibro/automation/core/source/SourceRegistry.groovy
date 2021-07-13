package latibro.automation.core.source

import latibro.automation.core.context.Context

class SourceRegistry {

    static final SourceRegistry INSTANCE = new SourceRegistry()

    private final List<SourceProvider> providers = []

    void register(SourceProvider provider) {
        providers.add(provider)
    }

    def <T> List<T> findSources(Class<T> type, Context context) {
        def sources = providers.findResults {
            it.getSource(type, context)
        }.unique()
        return sources as List<T>
    }

    def <T> T getSource(Class<T> type, Context context) {
        def sources = findSources(type, context)
        if (!sources) {
            return null
        }
        if (sources.size() > 1) {
            throw new Exception("Multiple (" + sources.size() + ") sources found: " + type)
        }
        return sources.first()
    }

}
