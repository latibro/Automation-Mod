package latibro.automation.core.api


import latibro.automation.core.context.Context
import latibro.automation.core.context.CoreContext

abstract class AbstractAPIProvider {

    private final ContextAPIRegistry registry = new ContextAPIRegistry()

    API provide(Context context) {
        if (context instanceof CoreContext) {
            def apiClass = registry.findAPIByContext(context)
            return apiClass.newInstance(context)
        }
        throw new NullPointerException()
    }

    private static final class ContextAPIRegistry {

        private List<ContextAPIEntry> entries = []

        void add(Class<Context> context, Class<API> api, String... names) {
            def entry = new ContextAPIEntry(
                    context: Objects.requireNonNull(context),
                    api: Objects.requireNonNull(api),
                    names: names
            )
            entries.add(entry)
        }

        Class<API> findAPIByContext(Context context) {
            return entries.find {
                it.context.isInstance(context)
            }?.api
        }

        private static class ContextAPIEntry {
            Class<Context> context
            Class<API> api
            String[] names
        }

    }

}