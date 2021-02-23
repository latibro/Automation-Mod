package latibro.automation.core.api.entity

import latibro.automation.AutomationMod
import latibro.automation.api.link.entity.EntityLinkAPI
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.context.entity.EntityLinkContext
import latibro.automation.core.context.entity.multi.EntityMultiLinkContext
import latibro.automation.nativeimpl.context.entity.multi.CoreEntityMultiLinkContext
import org.apache.logging.log4j.Logger
import spock.lang.Specification
import spock.lang.Unroll

class BaseEntityMultiLinkAPISpec extends Specification {

    def setupSpec() {
        AutomationMod.logger = Mock(Logger.class)
    }

    /* ********************************* */

    @Unroll("#test")
    def "Constructor - valid input"() {
        when:
        def api = new BaseEntityMultiLinkAPI(context as EntityMultiLinkContext)
        then:
        api != null
        where:
        test              | context
        "generic entity multi link" | Mock(EntityMultiLinkContext)
        "core entity multi link"    | Mock(CoreEntityMultiLinkContext)
    }

    @Unroll("#test")
    def "Constructor - invalid input"() {
        when:
        new BaseEntityMultiLinkAPI(context as EntityMultiLinkContext)
        then:
        thrown(Exception)
        where:
        test   | context
        "null" | null
    }

    /* ********************************* */

    @Unroll("#returnedFromContext")
    def "Count links - output count from context"() {
        given:
        def context = Mock(EntityMultiLinkContext, {
            count() >> returnedFromContext
        })
        def api = new BaseEntityMultiLinkAPI(context)
        when:
        def result = api.count()
        then:
        result == expected
        where:
        returnedFromContext | expected
        10                  | 10
        123                 | 123
    }

    /* ********************************* */

    @Unroll("#test")
    def "Get all links as list - validate expected size"() {
        given:
        def context = Mock(EntityMultiLinkContext, {
            asList() >> returnedFromContext
        })
        def api = new BaseEntityMultiLinkAPI(context)
        when:
        def result = api.asList()
        then:
        result.size() == expectedSize
        where:
        test          | returnedFromContext                                                         | expectedSize
        "empty list"  | []                                                                          | 0
        "list size 1" | [Mock(EntityLinkContext)]                                                   | 1
        "list size 3" | [Mock(EntityLinkContext), Mock(EntityLinkContext), Mock(EntityLinkContext)] | 3
    }

    def "Get all links as list - context output is null -> output empty list"() {
        given:
        def context = Mock(EntityMultiLinkContext, {
            asList() >> null
        })
        def api = new BaseEntityMultiLinkAPI(context)
        when:
        def result = api.asList()
        then:
        result.isEmpty()
    }

    def "Get all links as list - output is all wrapped as api"() {
        given:
        def entityContexts = [Mock(EntityLinkContext), Mock(EntityLinkContext), Mock(EntityLinkContext)]
        def context = Mock(EntityMultiLinkContext, {
            asList() >> entityContexts
        })
        def api = new BaseEntityMultiLinkAPI(context)
        when:
        def result = api.asList()
        then:
        result.every { it instanceof EntityLinkAPI }
        result.collect { ((ContextAPI) it).context }.sort() == entityContexts.sort()
    }

    /* ********************************* */

    //TODO test whereProperty()

}
