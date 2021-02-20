package latibro.automation.core.api.server


import latibro.automation.core.api.CoreAPI
import latibro.automation.core.context.server.ServerContext

final class CoreServerAPI extends BaseServerAPI implements CoreAPI {

    CoreServerAPI(ServerContext context) {
        super(context)
    }

}
