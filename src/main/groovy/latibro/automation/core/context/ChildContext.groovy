package latibro.automation.core.context

interface ChildContext extends Context{

    Context getParentContext();

}
