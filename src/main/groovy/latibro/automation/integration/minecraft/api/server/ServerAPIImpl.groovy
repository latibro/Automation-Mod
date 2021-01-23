package latibro.automation.integration.minecraft.api.server

import latibro.automation.core.context.server.ServerContext
import latibro.automation.integration.minecraft.api.entity.EntityCollectionAPI
import latibro.automation.integration.minecraft.api.entity.EntityCollectionAPIImpl

class ServerAPIImpl implements ServerAPI {

    private final ServerContext context

    ServerAPIImpl(ServerContext context) {
        this.context = Objects.requireNonNull(context)
    }

    @Override
    EntityCollectionAPI getAllLoadedEntity() {
        return new EntityCollectionAPIImpl(context.loadedEntitiesContext)
    }

}
