package latibro.automation.core.api.server

import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.api.entity.EntityGroupAPI
import latibro.automation.core.context.server.ServerContext

final class ServerAPIImpl implements ServerAPI, ContextAPI {

    private final ServerContext context

    ServerAPIImpl(ServerContext context) {
        this.context = Objects.requireNonNull(context)
    }

    @Override
    ServerContext getContext() {
        return context
    }

    @Override
    EntityGroupAPI getLoadedEntities() {
        return APIRegistry.getAPI(context.loadedEntitiesContext) as EntityGroupAPI
    }

}
