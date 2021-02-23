package latibro.automation.core.api.server

import latibro.automation.api.link.entity.EntityMultiLinkAPI
import latibro.automation.api.link.server.ServerLinkAPI
import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.BaseContextAPI
import latibro.automation.core.context.server.ServerLinkContext

class BaseServerLinkAPI extends BaseContextAPI<ServerLinkContext> implements ServerLinkAPI {

    BaseServerLinkAPI(ServerLinkContext context) {
        super(context)
    }

    @Override
    Boolean isLinked() {
        return context.isLinked()
    }

    @Override
    String getLinkType() {
        return context.getLinkType()
    }

    @Override
    EntityMultiLinkAPI getLoadedEntities() {
        return APIRegistry.getAPI(context.loadedEntities) as EntityMultiLinkAPI
    }

}
