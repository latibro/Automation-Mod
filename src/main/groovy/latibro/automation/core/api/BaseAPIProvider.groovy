package latibro.automation.core.api

import latibro.automation.api.API
import latibro.automation.core.api.entity.BaseEntityLinkAPI
import latibro.automation.core.api.entity.BaseEntityMultiLinkAPI
import latibro.automation.core.api.location.BaseLocationLinkAPI
import latibro.automation.core.api.server.BaseServerLinkAPI
import latibro.automation.core.api.world.BaseWorldLinkAPI
import latibro.automation.core.context.Context
import latibro.automation.core.context.entity.EntityContext
import latibro.automation.core.context.entity.group.EntityGroupContext
import latibro.automation.core.context.location.LocationContext
import latibro.automation.core.context.server.ServerContext
import latibro.automation.core.context.world.WorldContext

class BaseAPIProvider extends AbstractAPIProvider {

    @Override
    API getAPI(Context context) {
        if (context instanceof ServerContext) {
            return new BaseServerLinkAPI(context)
        }
        if (context instanceof WorldContext) {
            return new BaseWorldLinkAPI(context)
        }
        if (context instanceof LocationContext) {
            return new BaseLocationLinkAPI(context)
        }
        if (context instanceof EntityContext) {
            return new BaseEntityLinkAPI(context)
        }
        if (context instanceof EntityGroupContext) {
            return new BaseEntityMultiLinkAPI(context)
        }
        return super.getAPI(context)
    }

}