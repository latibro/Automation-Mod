package latibro.automation.core.api

import latibro.automation.core.context.Context

interface APIProvider {

    ContextAPI findContextAPI(Context context)

    List<String> getFeatureAPINames(Context context)

    FeatureAPI findFeatureAPI(String name, Context context)

}
