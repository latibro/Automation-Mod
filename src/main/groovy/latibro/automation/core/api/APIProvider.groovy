package latibro.automation.core.api

import latibro.automation.api.API
import latibro.automation.core.context.Context

interface APIProvider {

    API getAPI(Context context)

    List<String> getAPINames(API api)

    boolean hasAPI(String name, API api)

    API getAPI(String name, API api)

    API getAPI(Class<? extends API> cls, API api)

    API getAPI(Class<? extends API> cls, Context context)

}
