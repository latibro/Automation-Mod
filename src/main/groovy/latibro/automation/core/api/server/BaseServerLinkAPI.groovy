package latibro.automation.core.api.server

import latibro.automation.api.link.server.ServerLinkAPI
import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.ContextAPI
import latibro.automation.api.link.entity.EntityMultiLinkAPI
import latibro.automation.core.context.server.ServerContext

class BaseServerLinkAPI implements ServerLinkAPI, ContextAPI {

    private final ServerContext context

    BaseServerLinkAPI(ServerContext context) {
        this.context = Objects.requireNonNull(context)
    }

    @Override
    ServerContext getContext() {
        return context
    }

    @Override
    Boolean isLinked() {
        //TODO implement
        throw new RuntimeException("Not yet implemented")
    }

    @Override
    String getLinkType() {
        //TODO implement
        throw new RuntimeException("Not yet implemented")
    }

    @Override
    EntityMultiLinkAPI getLoadedEntities() {
        return APIRegistry.getAPI(context.loadedEntitiesContext) as EntityMultiLinkAPI
    }

}
