package latibro.automation.core.api.server


import latibro.automation.core.api.CoreAPI
import latibro.automation.core.context.server.ServerLinkContext

final class CoreServerLinkAPI extends BaseServerLinkAPI implements CoreAPI {

    CoreServerLinkAPI(ServerLinkContext context) {
        super(context)
    }

}
