package latibro.automation.core.api

import latibro.automation.core.context.Context

class BaseContextAPI<T extends Context> implements ContextAPI {

    private final T context

    BaseContextAPI(T context) {
        this.context = Objects.requireNonNull(context)
    }

    @Override
    T getContext() {
        return context
    }

}
