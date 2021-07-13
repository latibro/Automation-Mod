package latibro.automation.core.api


import latibro.automation.core.context.OldContext

class BaseContextAPI<T extends OldContext> implements ContextAPI {

    private final T context

    BaseContextAPI(T context) {
        this.context = Objects.requireNonNull(context)
    }

    @Override
    T getContext() {
        return context
    }

}
