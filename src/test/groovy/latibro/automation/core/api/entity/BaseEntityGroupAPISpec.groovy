package latibro.automation.core.api.entity

import latibro.automation.AutomationMod
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.context.CoreContext
import latibro.automation.core.context.entity.EntityContext
import latibro.automation.core.context.entity.group.EntityGroupContext
import org.apache.logging.log4j.Logger
import spock.lang.Specification
import spock.lang.Unroll

class BaseEntityGroupAPISpec extends Specification {

    def setupSpec() {
        AutomationMod.logger = Mock(Logger.class)
    }

    /* ********************************* */

    @Unroll("#test")
    def "Constructor - valid input"() {
        when:
        def api = new BaseEntityGroupAPI(context as EntityGroupContext)
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
        new BaseEntityGroupAPI(context as EntityGroupContext)
        then:
        thrown(Exception)
        where:
        test   | context
        "null" | null
    }

    /* ********************************* */

    @Unroll("#returnedFromContext")
    def "Get size of list - output size from context"() {
        given:
        def context = Mock(EntityGroupContext, {
            size() >> returnedFromContext
        })
        def api = new BaseEntityGroupAPI(context)
        when:
        def result = api.size()
        then:
        result == expected
        where:
        returnedFromContext | expected
        10                  | 10
        123                 | 123
    }

    /* ********************************* */

    @Unroll("#test")
    def "Get all as list - validate expected size"() {
        given:
        def context = Mock(EntityGroupContext, {
            getAll() >> returnedFromContext
        })
        def api = new BaseEntityGroupAPI(context)
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

    def "Get all as list - context output is null -> output empty list"() {
        given:
        def context = Mock(EntityGroupContext, {
            getAll() >> null
        })
        def api = new BaseEntityGroupAPI(context)
        when:
        def result = api.asList()
        then:
        result.isEmpty()
    }

    def "Get all as list - output is all wrapped as api"() {
        given:
        def entityContexts = [Mock(EntityContext), Mock(EntityContext), Mock(EntityContext)]
        def context = Mock(EntityGroupContext, {
            getAll() >> entityContexts
        })
        def api = new BaseEntityGroupAPI(context)
        when:
        def result = api.asList()
        then:
        result.every { it instanceof EntityAPI }
        result.collect { ((ContextAPI) it).context }.sort() == entityContexts.sort()
    }

    /* ********************************* */

    //TODO test whereProperty()

}
