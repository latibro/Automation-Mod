package latibro.automation.core.api.entity

import latibro.automation.AutomationMod
import latibro.automation.api.link.entity.EntityLinkAPI
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.context.CoreContext
import latibro.automation.core.context.entity.EntityContext
import latibro.automation.core.context.entity.group.EntityGroupContext
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
        def api = new BaseEntityMultiLinkAPI(context as EntityGroupContext)
        then:
        api != null
        where:
        test              | context
        "generic context" | Mock(EntityGroupContext)
        "core context"    | Mock(EntityGroupContext, additionalInterfaces: [CoreContext])
    }

    @Unroll("#test")
    def "Constructor - invalid input"() {
        when:
        new BaseEntityMultiLinkAPI(context as EntityGroupContext)
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
        def context = Mock(EntityGroupContext, {
            size() >> returnedFromContext
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
        def context = Mock(EntityGroupContext, {
            getAll() >> returnedFromContext
        })
        def api = new BaseEntityMultiLinkAPI(context)
        when:
        def result = api.asList()
        then:
        result.size() == expectedSize
        where:
        test          | returnedFromContext                                             | expectedSize
        "empty list"  | []                                                              | 0
        "list size 1" | [Mock(EntityContext)]                                           | 1
        "list size 3" | [Mock(EntityContext), Mock(EntityContext), Mock(EntityContext)] | 3
    }

    def "Get all links as list - context output is null -> output empty list"() {
        given:
        def context = Mock(EntityGroupContext, {
            getAll() >> null
        })
        def api = new BaseEntityMultiLinkAPI(context)
        when:
        def result = api.asList()
        then:
        result.isEmpty()
    }

    def "Get all links as list - output is all wrapped as api"() {
        given:
        def entityContexts = [Mock(EntityContext), Mock(EntityContext), Mock(EntityContext)]
        def context = Mock(EntityGroupContext, {
            getAll() >> entityContexts
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
