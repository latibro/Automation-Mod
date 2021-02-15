package latibro.automation.core.context

interface ContextProvider {

    Context getContext(Class<? extends Context> cls, Context context)

}