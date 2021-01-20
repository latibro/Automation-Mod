package latibro.automation.core.context

class ChildContext implements Context {

    @Delegate private final Context parentContext

    ChildContext(Context parentContext) {
        this.parentContext = Objects.requireNonNull(parentContext)
    }

    Context getParentContext() {
        return parentContext
    }

}
