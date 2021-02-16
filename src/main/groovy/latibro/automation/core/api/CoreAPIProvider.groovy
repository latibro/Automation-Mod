package latibro.automation.core.api


import latibro.automation.core.api.entity.CoreEntityAPI
import latibro.automation.core.api.entity.EntityGroupAPIImpl
import latibro.automation.core.api.location.LocationAPIImpl
import latibro.automation.core.api.server.ServerAPIImpl
import latibro.automation.core.api.world.WorldAPIImpl
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
                return new ServerAPIImpl(context)
            }
            if (context instanceof WorldContext) {
                return new WorldAPIImpl(context)
            }
            if (context instanceof LocationContext) {
                return new LocationAPIImpl(context)
            }
            if (context instanceof EntityContext) {
                return new CoreEntityAPI(context)
            }
            if (context instanceof EntityGroupContext) {
                return new EntityGroupAPIImpl(context)
            }
        }
        return super.getAPI(context)
    }

}