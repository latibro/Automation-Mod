package latibro.automation.core.api.world

import latibro.automation.AutomationMod
import latibro.automation.api.link.entity.EntityMultiLinkAPI
import latibro.automation.api.link.location.LocationLinkAPI
import latibro.automation.api.link.server.ServerLinkAPI
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.context.entity.multi.EntityMultiLinkContext
import latibro.automation.core.context.location.LocationContext
import latibro.automation.core.context.server.ServerLinkContext
import latibro.automation.core.context.world.WorldLinkContext
import latibro.automation.nativeimpl.context.world.CoreWorldLinkContext
import org.apache.logging.log4j.Logger
import spock.lang.PendingFeature
import spock.lang.Specification
import spock.lang.Unroll

class BaseWorldLinkAPISpec extends Specification {

    def setupSpec() {
        AutomationMod.logger = Mock(Logger.class)
    }

    @Unroll("#test")
    def "Constructor - success"() {
        when:
        def api = new BaseWorldLinkAPI(context as WorldLinkContext)
        then:
        api != null
        where:
        test                 | context
        "generic world link" | Mock(WorldLinkContext)
        "core world link"    | Mock(CoreWorldLinkContext)
    }

    @Unroll("#test")
    def "Constructor - fails"() {
        when:
        new BaseWorldLinkAPI(context as WorldLinkContext)
        then:
        thrown(Exception)
        where:
        test   | context
        "null" | null
    }

    @Unroll("#test")
    def "Get server"() {
        given:
        def context = Mock(WorldLinkContext, {
            getServer() >> returnedFromContext
        })
        def api = new BaseWorldLinkAPI(context)
        when:
        def result = api.getServer()
        then:
        expected.call(result)
        where:
        test                          | returnedFromContext     | expected
        "null"                        | null                    | { it == null }
        "generic server link"         | Mock(ServerLinkContext) | { it instanceof ServerLinkAPI }
        "expected context inside API" | Mock(ServerLinkContext) | { ((ContextAPI) it).context == returnedFromContext }
    }

    @Unroll("#test")
    def "Get loaded entities"() {
        given:
        def context = Mock(WorldLinkContext, {
            getLoadedEntities() >> returnedFromContext
        })
        def api = new BaseWorldLinkAPI(context)
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

    @Unroll("#test")
    def "Get location by coordinates - expected return"() {
        given:
        def context = Mock(WorldLinkContext, {
            getLocationByCoordinates(*_) >> returnedFromContext
        })
        def api = new BaseWorldLinkAPI(context)
        when:
        def result = api.getLocationByCoordinates(1, 1, 1)
        then:
        expected.call(result)
        where:
        test                          | returnedFromContext   | expected
        "null"                        | null                  | { it == null }
        "generic location link"       | Mock(LocationContext) | { it instanceof LocationLinkAPI }
        "expected context inside API" | Mock(LocationContext) | { ((ContextAPI) it).context == returnedFromContext }
    }

    @PendingFeature(
            reason = "Missing handling of null input",
            exceptions = [ClassCastException]
    )
    @Unroll("#test")
    def "Get location by coordinate - handling parameters"() {
        given:
        def context = Mock(WorldLinkContext)
        def api = new BaseWorldLinkAPI(context)
        when:
        api.getLocationByCoordinates(inputX as Number, inputY as Number, inputZ as Number)
        then:
        1 * context.getLocationByCoordinates(passedX, passedY, passedZ)
        where:
        test                       | inputX           | inputY           | inputZ           | passedX | passedY | passedZ
        "null"                     | null             | null             | null             | null    | null    | null
        "primitive int"            | 10               | 20               | 30               | 10      | 20      | 30
        "primitive double (round)" | 10.0             | 20.0             | 30.0             | 10      | 20      | 30
        "Double (round)"           | new Double(10.0) | new Double(20.0) | new Double(30.0) | 10      | 20      | 30
        "Double (low decimal)"     | new Double(10.1) | new Double(20.1) | new Double(30.1) | 10      | 20      | 30
        "Double (high decimal)"    | new Double(10.9) | new Double(20.9) | new Double(30.9) | 10      | 20      | 30
    }

}
