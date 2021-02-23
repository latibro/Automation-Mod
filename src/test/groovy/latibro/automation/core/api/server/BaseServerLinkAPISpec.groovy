package latibro.automation.core.api.server

import latibro.automation.AutomationMod
import latibro.automation.api.link.entity.EntityMultiLinkAPI
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.context.entity.multi.EntityMultiLinkContext
import latibro.automation.core.context.server.ServerLinkContext
import latibro.automation.nativeimpl.context.server.CoreServerLinkContext
import org.apache.logging.log4j.Logger
import spock.lang.Specification
import spock.lang.Unroll

class BaseServerLinkAPISpec extends Specification {

    def setupSpec() {
        AutomationMod.logger = Mock(Logger.class)
    }

    @Unroll("#test")
    def "Constructor - success"() {
        when:
        def api = new BaseServerLinkAPI(context as ServerLinkContext)
        then:
        api != null
        where:
        test                  | context
        "generic server link" | Mock(ServerLinkContext)
        "core server link"    | Mock(CoreServerLinkContext)
    }

    @Unroll("#test")
    def "Constructor - fails"() {
        when:
        new BaseServerLinkAPI(context as ServerLinkContext)
        then:
        thrown(Exception)
        where:
        test   | context
        "null" | null
    }

    @Unroll("#test")
    def "Get loaded entities"() {
        given:
        def context = Mock(ServerLinkContext, {
            getLoadedEntities() >> returnedFromContext
        })
        def api = new BaseServerLinkAPI(context)
        when:
        def result = api.getLoadedEntities()
        then:
        expected.call(result)
        where:
        test                          | returnedFromContext          | expected
        "null"                        | null                         | { it == null }
        "generic entity multi link"   | Mock(EntityMultiLinkContext) | { it instanceof EntityMultiLinkAPI }
        "expected context inside API" | Mock(EntityMultiLinkContext) | { ((ContextAPI) it).context == returnedFromContext }
    }

}
