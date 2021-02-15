package latibro.automation.core.context

abstract class AbstractContextProvider implements ContextProvider {

    @Override
    Context getContext(Class<? extends Context> cls, Context context) {
        return null
    }

}
