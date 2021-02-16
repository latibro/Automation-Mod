package latibro.automation.core.context

interface ContextProvider {

    boolean hasContext(Class<? extends Context> cls, Context context)

    Context getContext(Class<? extends Context> cls, Context context)

}