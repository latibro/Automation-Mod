package latibro.automation.core.api.location

import latibro.automation.AutomationMod
import latibro.automation.core.api.ContextAPI
import latibro.automation.api.link.entity.EntityMultiLinkAPI
import latibro.automation.api.link.world.WorldLinkAPI
import latibro.automation.core.context.CoreContext
import latibro.automation.core.context.entity.group.EntityGroupContext
import latibro.automation.core.context.location.LocationContext
import latibro.automation.core.context.world.WorldContext
import org.apache.logging.log4j.Logger
import spock.lang.PendingFeature
import spock.lang.Specification
import spock.lang.Title
import spock.lang.Unroll

@Title("LocationLinkAPI - Base impl")
class BaseLocationLinkAPISpec extends Specification {

    def setupSpec() {
        AutomationMod.logger = Mock(Logger.class)
    }

    @Unroll("#test")
    def "Constructor - success"() {
        when:
        def api = new BaseLocationLinkAPI(context as LocationContext)
        then:
        api != null
        where:
        test      | context
        "generic" | Mock(LocationContext)
        "core"    | Mock(LocationContext, additionalInterfaces: [CoreContext])
    }

    @Unroll("#test")
    def "Constructor - fails"() {
        when:
        new BaseLocationLinkAPI(context as LocationContext)
        then:
        thrown(Exception)
        where:
        test   | context
        "null" | null
    }

    @Unroll("#test")
    def "Is loaded"() {
        given:
        def context = Mock(LocationContext, {
            isLoaded() >> returnedFromContext
        })
        def api = new BaseLocationLinkAPI(context)
        when:
        def result = api.isLoaded()
        then:
        result == expected
        where:
        test         | returnedFromContext | expected
        "is loaded"  | true                | true
        "not loaded" | false               | false
    }

    @Unroll("#test")
    def "Get coordinate X"() {
        given:
        def context = Mock(LocationContext, {
            getX() >> returnedFromContext
        })
        def api = new BaseLocationLinkAPI(context)
        when:
        def result = api.getX()
        then:
        result == expected
        where:
        test            | returnedFromContext | expected
        "primitive int" | 10                  | 10
        "Integer"       | 10                  | new Integer(10)
    }

    @Unroll("#test")
    def "Get coordinate Y"() {
        given:
        def context = Mock(LocationContext, {
            getY() >> returnedFromContext
        })
        def api = new BaseLocationLinkAPI(context)
        when:
        def result = api.getY()
        then:
        result == expected
        where:
        test            | returnedFromContext | expected
        "primitive int" | 20                  | 20
        "Integer"       | 20                  | new Integer(20)
    }

    @Unroll("#test")
    def "Get coordinate Z"() {
        given:
        def context = Mock(LocationContext, {
            getZ() >> returnedFromContext
        })
        def api = new BaseLocationLinkAPI(context)
        when:
        def result = api.getZ()
        then:
        result == expected
        where:
        test            | returnedFromContext | expected
        "primitive int" | 30                  | 30
        "Integer"       | 30                  | new Integer(30)
    }

    @Unroll("#test")
    def "Get world"() {
        given:
        def context = Mock(LocationContext, {
            getWorldContext() >> returnedFromContext
        })
        def api = new BaseLocationLinkAPI(context)
        when:
        def result = api.getWorld()
        then:
        expected.call(result)
        where:
        test             | returnedFromContext | expected
        "null"           | null                | { it == null }
        "generic server" | Mock(WorldContext)  | {
            it instanceof WorldLinkAPI &&
                    ((ContextAPI) it).context == returnedFromContext
        }
    }

    @Unroll("#test")
    def "Get entities"() {
        given:
        def context = Mock(LocationContext, {
            getEntities() >> returnedFromContext
        })
        def api = new BaseLocationLinkAPI(context)
        when:
        def result = api.getEntities()
        then:
        expected.call(result)
        where:
        test             | returnedFromContext      | expected
        "null"           | null                     | { it == null }
        "generic server" | Mock(EntityGroupContext) | {
            it instanceof EntityMultiLinkAPI &&
                    ((ContextAPI) it).context == returnedFromContext
        }
    }

    @Unroll("#test")
    def "Get distance to coordinate - expected return"() {
        given:
        def context = Mock(LocationContext, {
            getDistanceToCoordinates(*_) >> returnedFromContext
        })
        def api = new BaseLocationLinkAPI(context)
        when:
        def result = api.getDistanceToCoordinates(1, 1, 1)
        then:
        result == expected
        where:
        test            | returnedFromContext | expected
        "primitive int" | 5                   | 5
        "Integer"       | 5                   | new Integer(5)
    }

    @PendingFeature(
            reason = "Missing handling of null input",
            exceptions = [ClassCastException]
    )
    @Unroll("#test")
    def "Get distance to coordinate - handling parameters"() {
        given:
        def context = Mock(LocationContext)
        def api = new BaseLocationLinkAPI(context)
        when:
        api.getDistanceToCoordinates(inputX as Number, inputY as Number, inputZ as Number)
        then:
        1 * context.getDistanceToCoordinates(passedX, passedY, passedZ)
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
