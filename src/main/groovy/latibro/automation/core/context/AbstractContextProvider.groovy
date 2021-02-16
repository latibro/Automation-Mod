package latibro.automation.core.context

abstract class AbstractContextProvider implements ContextProvider {

    @Override
    boolean hasContext(Class<? extends Context> cls, Context context) {
        return false
    }

    @Override
    Context getContext(Class<? extends Context> cls, Context context) {
        return null
    }

}
