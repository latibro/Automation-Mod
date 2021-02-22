package latibro.automation.core.api

import latibro.automation.core.api.entity.CoreEntityLinkAPI
import latibro.automation.core.api.entity.CoreEntityMultiLinkAPI
import latibro.automation.core.api.location.CoreLocationLinkAPI
import latibro.automation.core.api.server.CoreServerLinkAPI
import latibro.automation.core.api.world.CoreWorldLinkAPI
import latibro.automation.core.context.Context
import latibro.automation.core.context.CoreContext
import latibro.automation.core.context.entity.EntityContext
import latibro.automation.core.context.entity.group.EntityGroupContext
import latibro.automation.core.context.location.LocationContext
import latibro.automation.core.context.server.ServerContext
import latibro.automation.core.context.world.WorldContext
import spock.lang.Specification
import spock.lang.Unroll

class CoreAPIProviderSpec extends Specification {

    @Unroll("#test")
    def "Get API from context - is providing"() {
        given:
        def provider = new CoreAPIProvider()
        when:
        def api = provider.getAPI(context as Context)
        then:
        apiClass.isInstance(api)
        where:
        test                | context                                                       | apiClass
        "core server"       | Mock(ServerContext, additionalInterfaces: [CoreContext])      | CoreServerLinkAPI
        "core world"        | Mock(WorldContext, additionalInterfaces: [CoreContext])       | CoreWorldLinkAPI
        "core location"     | Mock(LocationContext, additionalInterfaces: [CoreContext])    | CoreLocationLinkAPI
        "core entity"       | Mock(EntityContext, additionalInterfaces: [CoreContext])      | CoreEntityLinkAPI
        "core entity group" | Mock(EntityGroupContext, additionalInterfaces: [CoreContext]) | CoreEntityMultiLinkAPI
    }

    @Unroll("#test")
    def "Get API from context - is NOT providing"() {
        given:
        def provider = new CoreAPIProvider()
        when:
        def api = provider.getAPI(context)
        then:
        api == null
        where:
        test                   | context
        "null"                 | null
        "random"               | Mock(Context)
        "generic server"       | Mock(ServerContext)
        "generic world"        | Mock(WorldContext)
        "generic location"     | Mock(LocationContext)
        "generic entity"       | Mock(EntityContext)
        "generic entity group" | Mock(EntityGroupContext)
    }

}
