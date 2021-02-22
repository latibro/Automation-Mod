package latibro.automation.core.api

import latibro.automation.api.API
import latibro.automation.core.api.entity.CoreEntityLinkAPI
import latibro.automation.core.api.entity.CoreEntityMultiLinkAPI
import latibro.automation.core.api.location.CoreLocationLinkAPI
import latibro.automation.core.api.server.CoreServerLinkAPI
import latibro.automation.core.api.world.CoreWorldLinkAPI
import latibro.automation.core.context.Context
import latibro.automation.core.context.CoreContext
import latibro.automation.core.context.entity.EntityContext
import latibro.automation.core.context.entity.group.EntityGroupContext
import latibro.automation.core.context.location.LocationContext
import latibro.automation.core.context.server.ServerContext
import latibro.automation.core.context.world.WorldContext

final class CoreAPIProvider extends AbstractAPIProvider {

    @Override
    API getAPI(Context context) {
        if (context instanceof CoreContext) {
            if (context instanceof ServerContext) {
                return new CoreServerLinkAPI(context)
            }
            if (context instanceof WorldContext) {
                return new CoreWorldLinkAPI(context)
            }
            if (context instanceof LocationContext) {
                return new CoreLocationLinkAPI(context)
            }
            if (context instanceof EntityContext) {
                return new CoreEntityLinkAPI(context)
            }
            if (context instanceof EntityGroupContext) {
                return new CoreEntityMultiLinkAPI(context)
            }
        }
        return super.getAPI(context)
    }

}