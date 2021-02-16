package latibro.automation.core.api

import latibro.automation.core.context.Context

abstract class AbstractAPIProvider implements APIProvider {

    //TODO static map "name"->"api class"
    //TODO static map "api class"->"context class"
    //TODO static map "context class"->"api constructor"

    @Override
    API getAPI(Context context) {
        return null
    }

    @Override
    List<String> getAPINames(API contextApi) {
        return []
    }

    @Override
    boolean hasAPI(String name, API contextApi) {
        return false
    }

    @Override
    API getAPI(String name, API contextApi) {
        return null
    }

    @Override
    API getAPI(Class<? extends API> apiClass, API contextApi) {
        return null
    }

    @Override
    API getAPI(Class<? extends API> apiClass, Context context) {
        return null
    }

}