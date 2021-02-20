package latibro.automation.core.api.world

import latibro.automation.AutomationMod
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.api.entity.EntityGroupAPI
import latibro.automation.core.api.location.LocationAPI
import latibro.automation.core.api.server.ServerAPI
import latibro.automation.core.context.CoreContext
import latibro.automation.core.context.entity.group.EntityGroupContext
import latibro.automation.core.context.location.LocationContext
import latibro.automation.core.context.server.ServerContext
import latibro.automation.core.context.world.WorldContext
import org.apache.logging.log4j.Logger
import spock.lang.PendingFeature
import spock.lang.Specification
import spock.lang.Unroll

class BaseWorldAPISpec extends Specification {

    def setupSpec() {
        AutomationMod.logger = Mock(Logger.class)
    }

    @Unroll("#test")
    def "Constructor - success"() {
        when:
        def api = new BaseWorldAPI(context as WorldContext)
        then:
        api != null
        where:
        test      | context
        "generic" | Mock(WorldContext)
        "core"    | Mock(WorldContext, additionalInterfaces: [CoreContext])
    }

    @Unroll("#test")
    def "Constructor - fails"() {
        when:
        new BaseWorldAPI(context as WorldContext)
        then:
        thrown(Exception)
        where:
        test   | context
        "null" | null
    }

    @Unroll("#test")
    def "Get server"() {
        given:
        def context = Mock(WorldContext, {
            getServerContext() >> returnedFromContext
        })
        def api = new BaseWorldAPI(context)
        when:
        def result = api.getServer()
        then:
        expected.call(result)
        where:
        test             | returnedFromContext | expected
        "null"           | null                | { it == null }
        "generic" | Mock(ServerContext) | {
            it instanceof ServerAPI &&
                    ((ContextAPI) it).context == returnedFromContext
        }
    }

    @Unroll("#test")
    def "Get loaded entities"() {
        given:
        def context = Mock(WorldContext, {
            getLoadedEntitiesContext() >> returnedFromContext
        })
        def api = new BaseWorldAPI(context)
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

    @Unroll("#test")
    def "Get location by coordinate - expected return"() {
        given:
        def context = Mock(WorldContext, {
            getLocationContextByCoordinates(*_) >> returnedFromContext
        })
        def api = new BaseWorldAPI(context)
        when:
        def result = api.getLocationByCoordinates(1, 1, 1)
        then:
        expected.call(result)
        where:
        test             | returnedFromContext   | expected
        "null"           | null                  | { it == null }
        "generic server" | Mock(LocationContext) | {
            it instanceof LocationAPI &&
                    ((ContextAPI) it).context == returnedFromContext
        }
    }

    @PendingFeature(
            reason = "Missing handling of null input",
            exceptions = [ClassCastException]
    )
    @Unroll("#test")
    def "Get location by coordinate - handling parameters"() {
        given:
        def context = Mock(WorldContext)
        def api = new BaseWorldAPI(context)
        when:
        api.getLocationByCoordinates(inputX as Number, inputY as Number, inputZ as Number)
        then:
        1 * context.getLocationContextByCoordinates(passedX, passedY, passedZ)
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
