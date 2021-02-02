package latibro.automation.core.api.server

import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.entity.EntityCollectionAPI
import latibro.automation.core.context.server.ServerContext

final class ServerAPIImpl implements ServerAPI {

    private final ServerContext context

    ServerAPIImpl(ServerContext context) {
        this.context = Objects.requireNonNull(context)
    }

    @Override
    EntityCollectionAPI getLoadedEntities() {
        return (EntityCollectionAPI) APIRegistry.getContextAPI(context.loadedEntityCollectionContext)
    }

}
