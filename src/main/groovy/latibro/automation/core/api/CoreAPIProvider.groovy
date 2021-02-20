package latibro.automation.core.api


import latibro.automation.core.api.entity.CoreEntityAPI
import latibro.automation.core.api.entity.CoreEntityGroupAPI
import latibro.automation.core.api.location.CoreLocationAPI
import latibro.automation.core.api.server.CoreServerAPI
import latibro.automation.core.api.world.CoreWorldAPI
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
                return new CoreServerAPI(context)
            }
            if (context instanceof WorldContext) {
                return new CoreWorldAPI(context)
            }
            if (context instanceof LocationContext) {
                return new CoreLocationAPI(context)
            }
            if (context instanceof EntityContext) {
                return new CoreEntityAPI(context)
            }
            if (context instanceof EntityGroupContext) {
                return new CoreEntityGroupAPI(context)
            }
        }
        return super.getAPI(context)
    }

}