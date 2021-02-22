package latibro.automation.core.api

import latibro.automation.core.api.entity.BaseEntityLinkAPI
import latibro.automation.core.api.entity.BaseEntityMultiLinkAPI
import latibro.automation.core.api.location.BaseLocationLinkAPI
import latibro.automation.core.api.server.BaseServerLinkAPI
import latibro.automation.core.api.world.BaseWorldLinkAPI
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
        "server"       | Mock(ServerContext)      | BaseServerLinkAPI
        "world"        | Mock(WorldContext)       | BaseWorldLinkAPI
        "location"     | Mock(LocationContext)    | BaseLocationLinkAPI
        "entity"       | Mock(EntityContext)      | BaseEntityLinkAPI
        "entity group" | Mock(EntityGroupContext) | BaseEntityMultiLinkAPI
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
