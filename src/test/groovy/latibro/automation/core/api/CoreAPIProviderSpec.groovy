package latibro.automation.core.api

import latibro.automation.core.api.entity.CoreEntityLinkAPI
import latibro.automation.core.api.entity.CoreEntityMultiLinkAPI
import latibro.automation.core.api.location.CoreLocationLinkAPI
import latibro.automation.core.api.server.CoreServerLinkAPI
import latibro.automation.core.api.world.CoreWorldLinkAPI
import latibro.automation.core.context.Context
import latibro.automation.core.context.entity.EntityLinkContext
import latibro.automation.core.context.entity.multi.EntityMultiLinkContext
import latibro.automation.core.context.location.LocationContext
import latibro.automation.core.context.server.ServerLinkContext
import latibro.automation.core.context.world.WorldLinkContext
import latibro.automation.nativeimpl.context.entity.CoreEntityLinkContext
import latibro.automation.nativeimpl.context.entity.multi.CoreEntityMultiLinkContext
import latibro.automation.nativeimpl.context.location.CoreLocationContext
import latibro.automation.nativeimpl.context.server.CoreServerLinkContext
import latibro.automation.nativeimpl.context.world.CoreWorldLinkContext
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
        test                     | context                          | apiClass
        "core server link"       | Mock(CoreServerLinkContext)      | CoreServerLinkAPI
        "core world link "       | Mock(CoreWorldLinkContext)       | CoreWorldLinkAPI
        "core location link"     | Mock(CoreLocationContext)        | CoreLocationLinkAPI
        "core entity link"       | Mock(CoreEntityLinkContext)      | CoreEntityLinkAPI
        "core entity multi link" | Mock(CoreEntityMultiLinkContext) | CoreEntityMultiLinkAPI
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
        test                        | context
        "null"                      | null
        "random context"            | Mock(Context)
        "generic server link"       | Mock(ServerLinkContext)
        "generic world link"        | Mock(WorldLinkContext)
        "generic location link"     | Mock(LocationContext)
        "generic entity link"       | Mock(EntityLinkContext)
        "generic entity multi link" | Mock(EntityMultiLinkContext)
    }

}
