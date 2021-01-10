package latibro.automation.api.context;

public interface Context<F> {

    public F getFacade();

    public <C> Context<C> createChildContext(C facade);

}
