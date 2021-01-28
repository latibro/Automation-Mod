package latibro.automation.core.api

import latibro.automation.core.api.entity.EntityAPIImpl
import latibro.automation.core.api.entity.EntityCollectionAPIImpl
import latibro.automation.core.api.position.PositionAPIImpl
import latibro.automation.core.api.server.ServerAPIImpl
import latibro.automation.core.api.world.WorldAPIImpl
import latibro.automation.core.context.Context
import latibro.automation.core.context.CoreContext
import latibro.automation.core.context.entity.EntityContext
import latibro.automation.core.context.entity.collection.EntityCollectionContext
import latibro.automation.core.context.position.PositionContext
import latibro.automation.core.context.server.ServerContext
import latibro.automation.core.context.world.WorldContext

final class CoreAPIProvider implements APIProvider {

    @Override
    ContextAPI findContextAPI(Context context) {
        if (context instanceof CoreContext) {
            if (context instanceof ServerContext) {
                return new ServerAPIImpl(context)
            }
            if (context instanceof WorldContext) {
                return new WorldAPIImpl(context)
            }
            if (context instanceof PositionContext) {
                return new PositionAPIImpl(context)
            }
            if (context instanceof EntityContext) {
                return new EntityAPIImpl(context)
            }
            if (context instanceof EntityCollectionContext) {
                return new EntityCollectionAPIImpl(context)
            }
        }
        return null
    }

    List<String> getFeatureAPINames(Context context) {
        return null
    }

    FeatureAPI findFeatureAPI(String name, Context context) {
        return null
    }

}