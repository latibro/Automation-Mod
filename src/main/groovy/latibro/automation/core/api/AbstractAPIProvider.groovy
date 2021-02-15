package latibro.automation.core.api

import latibro.automation.core.context.Context

abstract class AbstractAPIProvider implements APIProvider {

    //TODO static map "name"->"api class"
    //TODO static map "api class"->"context class"
    //TODO static map "context class"->"api constructor"

    @Override
    boolean hasAPI(Context context) {
        return false
    }

    @Override
    ContextAPI getAPI(Context context) {
        return null
    }

    @Override
    List<String> getAPINames(API api) {
        return []
    }

    @Override
    boolean hasAPI(String name, API api) {
        return false
    }

    @Override
    API getAPI(String name, API api) {
        return null
    }

    @Override
    API getAPI(Class<? extends API> cls, API api) {
        return null
    }

}