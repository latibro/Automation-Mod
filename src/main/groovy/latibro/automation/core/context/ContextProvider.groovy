package latibro.automation.core.context

interface ContextProvider {

    Context createContext();

    Context createContext(Map properties);

}
