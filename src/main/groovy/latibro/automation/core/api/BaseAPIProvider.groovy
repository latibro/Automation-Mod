package latibro.automation.core.api

import latibro.automation.core.api.entity.BaseEntityAPI
import latibro.automation.core.api.entity.BaseEntityGroupAPI
import latibro.automation.core.api.location.BaseLocationAPI
import latibro.automation.core.api.server.BaseServerAPI
import latibro.automation.core.api.world.BaseWorldAPI
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
            return new BaseServerAPI(context)
        }
        if (context instanceof WorldContext) {
            return new BaseWorldAPI(context)
        }
        if (context instanceof LocationContext) {
            return new BaseLocationAPI(context)
        }
        if (context instanceof EntityContext) {
            return new BaseEntityAPI(context)
        }
        if (context instanceof EntityGroupContext) {
            return new BaseEntityGroupAPI(context)
        }
        return super.getAPI(context)
    }

}