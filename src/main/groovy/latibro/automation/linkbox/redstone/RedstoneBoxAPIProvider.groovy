package latibro.automation.linkbox.redstone

import latibro.automation.api.API
import latibro.automation.core.api.AbstractAPIProvider
import latibro.automation.core.api.ContextAPI
import latibro.automation.nativeimpl.context.tileentity.CoreTileEntityLinkContext

class RedstoneBoxAPIProvider extends AbstractAPIProvider {

    @Override
    List<String> getAPINames(API providingApi) {
        if (providingApi instanceof ContextAPI) {
            if (providingApi.context instanceof CoreTileEntityLinkContext) {
                return ["automation:redstone_box"]
            }
        }
        return []
    }

    @Override
    API getAPI(String apiName, API providingApi) {
        if (hasAPI(apiName, providingApi)) {
            def context = (CoreTileEntityLinkContext) ((ContextAPI) providingApi).context
            return new RedstoneBoxAPIImpl(context)
        }
        return null
    }

}
