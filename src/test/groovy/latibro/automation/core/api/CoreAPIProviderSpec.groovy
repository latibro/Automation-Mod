package latibro.automation.core.api

import latibro.automation.core.api.entity.CoreEntityAPI
import latibro.automation.core.api.entity.CoreEntityGroupAPI
import latibro.automation.core.api.location.CoreLocationAPI
import latibro.automation.core.api.server.CoreServerAPI
import latibro.automation.core.api.world.CoreWorldAPI
import latibro.automation.core.context.Context
import latibro.automation.core.context.CoreContext
import latibro.automation.core.context.entity.group.EntityGroupContext
import latibro.automation.core.context.location.LocationContext
import latibro.automation.core.context.server.ServerContext
import latibro.automation.core.context.world.WorldContext
import latibro.automation.integration.minecraft.context.entity.EntityContext
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
        "core server"       | Mock(ServerContext, additionalInterfaces: [CoreContext])      | CoreServerAPI
        "core world"        | Mock(WorldContext, additionalInterfaces: [CoreContext])       | CoreWorldAPI
        "core location"     | Mock(LocationContext, additionalInterfaces: [CoreContext])    | CoreLocationAPI
        "core entity"       | Mock(EntityContext, additionalInterfaces: [CoreContext])      | CoreEntityAPI
        "core entity group" | Mock(EntityGroupContext, additionalInterfaces: [CoreContext]) | CoreEntityGroupAPI
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
