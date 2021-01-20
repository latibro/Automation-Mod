package latibro.automation.core.context

abstract class RootContext implements Context {

    @Override
    Context getRootContext() {
        return this
    }

}
