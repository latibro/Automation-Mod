package latibro.automation.core.context.server

import groovy.transform.CompileStatic
import latibro.automation.core.api.server.ServerAPI
import latibro.automation.core.api.server.ServerAPIImpl

@CompileStatic
abstract class AbstractServerContext implements ServerContext {

    @Override
    ServerAPI getAPI() {
        return new ServerAPIImpl(this)
    }

}