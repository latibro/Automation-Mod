package latibro.automation.core.api.entity

import latibro.automation.AutomationMod
import latibro.automation.api.link.location.LocationLinkAPI
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.context.entity.EntityLinkContext
import latibro.automation.core.context.location.LocationLinkContext
import latibro.automation.nativeimpl.context.entity.CoreEntityLinkContext
import org.apache.logging.log4j.Logger
import spock.lang.Specification
import spock.lang.Title
import spock.lang.Unroll

@Title("EntityLinkAPI - Base impl")
class BaseEntityLinkAPISpec extends Specification {

    def setupSpec() {
        AutomationMod.logger = Mock(Logger.class)
    }

    @Unroll("#test")
    def "Constructor - success"() {
        when:
        def api = new BaseEntityLinkAPI(context as EntityLinkContext)
        then:
        api != null
        where:
        test                  | context
        "generic entity link" | Mock(EntityLinkContext)
        "core entity link"    | Mock(CoreEntityLinkContext)
    }

    @Unroll("#test")
    def "Constructor - fails"() {
        when:
        new BaseEntityLinkAPI(context as EntityLinkContext)
        then:
        thrown(Exception)
        where:
        test   | context
        "null" | null
    }

    @Unroll("#test")
    def "Is loaded"() {
        given:
        def context = Mock(EntityLinkContext, {
            isLoaded() >> returnedFromContext
        })
        def api = new BaseEntityLinkAPI(context)
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
    def "Get UUID"() {
        given:
        def context = Mock(EntityLinkContext, {
            getUUID() >> returnedFromContext
        })
        def api = new BaseEntityLinkAPI(context)
        when:
        def result = api.getUUID()
        then:
        result == expected
        where:
        test    | returnedFromContext                                     | expected
        "null"  | null                                                    | null
        "valid" | UUID.fromString("fa724e51-c0ab-4853-98cb-cd4670d16c63") | "fa724e51-c0ab-4853-98cb-cd4670d16c63"
    }


    @Unroll("#test")
    def "Get location"() {
        given:
        def context = Mock(EntityLinkContext, {
            getLocation() >> returnedFromContext
        })
        def api = new BaseEntityLinkAPI(context)
        when:
        def result = api.getLocation()
        then:
        expected.call(result)
        where:
        test                          | returnedFromContext   | expected
        "null"                        | null                  | { it == null }
        "generic location link"       | Mock(LocationLinkContext) | { it instanceof LocationLinkAPI }
        "expected context inside API" | Mock(LocationLinkContext) | { ((ContextAPI) it).context == returnedFromContext }
    }

    @Unroll("#test")
    def "Get name"() {
        given:
        def context = Mock(EntityLinkContext, {
            getName() >> returnedFromContext
        })
        def api = new BaseEntityLinkAPI(context)
        when:
        def result = api.getName()
        then:
        result == expected
        where:
        test    | returnedFromContext | expected
        "null"  | null                | null
        "valid" | "name"              | "name"
    }

    @Unroll("#test")
    def "Get type"() {
        given:
        def context = Mock(EntityLinkContext, {
            getType() >> returnedFromContext
        })
        def api = new BaseEntityLinkAPI(context)
        when:
        def result = api.getType()
        then:
        result == expected
        where:
        test    | returnedFromContext | expected
        "null"  | null                | null
        "valid" | "type"              | "type"
    }

}
