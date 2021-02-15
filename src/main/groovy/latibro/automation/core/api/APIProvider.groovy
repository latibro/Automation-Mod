package latibro.automation.core.api

import latibro.automation.core.context.Context

interface APIProvider {

    boolean hasAPI(Context context)

    ContextAPI getAPI(Context context)

    List<String> getAPINames(API api)

    boolean hasAPI(String name, API api)

    API getAPI(String name, API api)

    API getAPI(Class<? extends API> cls, API api)

}
