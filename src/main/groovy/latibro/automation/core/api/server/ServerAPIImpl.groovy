package latibro.automation.core.api.server

import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.entity.EntityGroupAPI
import latibro.automation.core.context.server.ServerContext

final class ServerAPIImpl implements ServerAPI {

    private final ServerContext context

    ServerAPIImpl(ServerContext context) {
        this.context = Objects.requireNonNull(context)
    }

    @Override
    EntityGroupAPI getLoadedEntities() {
        return (EntityGroupAPI) APIRegistry.getContextAPI(context.loadedEntitiesContext)
    }

}
