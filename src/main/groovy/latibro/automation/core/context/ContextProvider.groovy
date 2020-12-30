package latibro.automation.core.context

public interface ContextProvider {

    Context createContext();

    Context createContext(Map properties);

}
