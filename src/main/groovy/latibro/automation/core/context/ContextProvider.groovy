package latibro.automation.core.context;

import java.util.Map;

public interface ContextProvider {

    Context createContext();

    Context createContext(Map properties);

}
