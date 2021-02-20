package latibro.automation.core.api.server

import latibro.automation.AutomationMod
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.api.entity.EntityGroupAPI
import latibro.automation.core.context.CoreContext
import latibro.automation.core.context.entity.group.EntityGroupContext
import latibro.automation.core.context.server.ServerContext
import org.apache.logging.log4j.Logger
import spock.lang.Specification
import spock.lang.Unroll

class BaseServerAPISpec extends Specification {

    def setupSpec() {
        AutomationMod.logger = Mock(Logger.class)
    }

    @Unroll("#test")
    def "Constructor - success"() {
        when:
        def api = new BaseServerAPI(context as ServerContext)
        then:
        api != null
        where:
        test      | context
        "generic" | Mock(ServerContext)
        "core"    | Mock(ServerContext, additionalInterfaces: [CoreContext])
    }

    @Unroll("#test")
    def "Constructor - fails"() {
        when:
        new BaseServerAPI(context as ServerContext)
        then:
        thrown(Exception)
        where:
        test   | context
        "null" | null
    }

    @Unroll("#test")
    def "Get loaded entities"() {
        given:
        def context = Mock(ServerContext, {
            getLoadedEntitiesContext() >> returnedFromContext
        })
        def api = new BaseServerAPI(context)
        when:
        def result = api.getLoadedEntities()
        then:
        expected.call(result)
        where:
        test             | returnedFromContext      | expected
        "null"           | null                     | { it == null }
        "generic server" | Mock(EntityGroupContext) | {
            it instanceof EntityGroupAPI &&
                    ((ContextAPI) it).context == returnedFromContext
        }
    }

}
