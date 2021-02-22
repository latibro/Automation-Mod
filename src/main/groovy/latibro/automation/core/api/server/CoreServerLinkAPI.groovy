package latibro.automation.core.api.server


import latibro.automation.core.api.CoreAPI
import latibro.automation.core.context.server.ServerContext

final class CoreServerLinkAPI extends BaseServerLinkAPI implements CoreAPI {

    CoreServerLinkAPI(ServerContext context) {
        super(context)
    }

}
