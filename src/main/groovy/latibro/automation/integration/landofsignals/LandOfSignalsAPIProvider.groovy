package latibro.automation.integration.landofsignals


import latibro.automation.api.API
import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.AbstractAPIProvider
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.api.location.CoreLocationLinkAPI
import latibro.automation.core.context.Context
import latibro.automation.core.context.ContextRegistry

final class LandOfSignalsAPIProvider extends AbstractAPIProvider {

    @Override
    API getAPI(Context context) {
        if (context instanceof SignalContext) {
            return new SignalAPIImpl(context)
        }
        return super.getAPI(context)
    }

    @Override
    List<String> getAPINames(API api) {
        def names = super.getAPINames(api)
        if (api instanceof CoreLocationLinkAPI) {
            names.add("landofsignals:signal")
        }
        return names
    }

    @Override
    boolean hasAPI(String name, API api) {
        if (api instanceof CoreLocationLinkAPI) {
            if (name == "landofsignals:signal") {
                return true
            }
        }
        return super.hasAPI(name, api)
    }

    @Override
    API getAPI(String name, API api) {
        if (hasAPI(name, api)) {
            if (api instanceof CoreLocationLinkAPI) {
                if (name == "landofsignals:signal") {
                    return getAPI(SignalAPI, api)
                }
            }
        }
        return super.getAPI(name, api)
    }

    @Override
    API getAPI(Class<? extends API> cls, API api) {
        if (api instanceof ContextAPI) {
            if (cls == SignalAPI) {
                def context = ContextRegistry.getContext(SignalContext, api.context)
                return APIRegistry.getAPI(context)
            }
        }
        return super.getAPI(cls, api)
    }

}