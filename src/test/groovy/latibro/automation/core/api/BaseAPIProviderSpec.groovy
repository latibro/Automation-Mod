package latibro.automation.core.api

import latibro.automation.core.api.entity.BaseEntityAPI
import latibro.automation.core.api.entity.BaseEntityGroupAPI
import latibro.automation.core.api.location.BaseLocationAPI
import latibro.automation.core.api.server.BaseServerAPI
import latibro.automation.core.api.world.BaseWorldAPI
import latibro.automation.core.context.Context
import latibro.automation.core.context.entity.EntityContext
import latibro.automation.core.context.entity.group.EntityGroupContext
import latibro.automation.core.context.location.LocationContext
import latibro.automation.core.context.server.ServerContext
import latibro.automation.core.context.world.WorldContext
import spock.lang.Specification
import spock.lang.Unroll

class BaseAPIProviderSpec extends Specification {

    @Unroll("#test")
    def "Get API from context - is providing"() {
        given:
        def provider = new BaseAPIProvider()
        when:
        def api = provider.getAPI(context)
        then:
        apiClass.isInstance(api)
        where:
        test           | context                  | apiClass
        "server"       | Mock(ServerContext)      | BaseServerAPI
        "world"        | Mock(WorldContext)       | BaseWorldAPI
        "location"     | Mock(LocationContext)    | BaseLocationAPI
        "entity"       | Mock(EntityContext)      | BaseEntityAPI
        "entity group" | Mock(EntityGroupContext) | BaseEntityGroupAPI
    }

    @Unroll("#test")
    def "Get API from context - is NOT providing"() {
        given:
        def provider = new BaseAPIProvider()
        when:
        def api = provider.getAPI(context)
        then:
        api == null
        where:
        test     | context
        "null"   | null
        "random" | Mock(Context)
    }

}
