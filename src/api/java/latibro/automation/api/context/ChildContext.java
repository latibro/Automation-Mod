package latibro.automation.api.context;

public interface ChildContext<F> extends Context<F> {

    public Context getParentContext();

}
