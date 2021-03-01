package latibro.automation.core.api

import latibro.automation.core.api.entity.BaseEntityLinkAPI
import latibro.automation.core.api.entity.BaseEntityMultiLinkAPI
import latibro.automation.core.api.location.BaseLocationLinkAPI
import latibro.automation.core.api.server.BaseServerLinkAPI
import latibro.automation.core.api.world.BaseWorldLinkAPI
import latibro.automation.core.context.Context
import latibro.automation.core.context.entity.EntityLinkContext
import latibro.automation.core.context.entity.multi.EntityMultiLinkContext
import latibro.automation.core.context.location.LocationLinkContext
import latibro.automation.core.context.server.ServerLinkContext
import latibro.automation.core.context.world.WorldLinkContext
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
        test                | context                      | apiClass
        "server link"       | Mock(ServerLinkContext)      | BaseServerLinkAPI
        "world link"        | Mock(WorldLinkContext)       | BaseWorldLinkAPI
        "location link"     | Mock(LocationLinkContext) | BaseLocationLinkAPI
        "entity link"       | Mock(EntityLinkContext)      | BaseEntityLinkAPI
        "entity multi link" | Mock(EntityMultiLinkContext) | BaseEntityMultiLinkAPI
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
        test             | context
        "null"           | null
        "random context" | Mock(Context)
    }

}
