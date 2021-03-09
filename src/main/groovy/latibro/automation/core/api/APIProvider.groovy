package latibro.automation.core.api

import latibro.automation.api.API
import latibro.automation.core.context.Context

interface APIProvider {

    API getAPI(Context context)

    List<String> getAPINames(API providingApi)

    boolean hasAPI(String apiName, API providingApi)

    API getAPI(String apiName, API providingApi)

    API getAPI(Class<? extends API> apiClass, API providingApi)

    API getAPI(Class<? extends API> apiClass, Context context)

}
