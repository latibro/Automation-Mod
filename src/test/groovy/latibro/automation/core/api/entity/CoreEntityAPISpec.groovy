package latibro.automation.core.api.entity

import latibro.automation.core.context.CoreContext
import latibro.automation.core.context.entity.EntityContext
import spock.lang.PendingFeature
import spock.lang.Specification
import spock.lang.Title
import spock.lang.Unroll

@Title("EntityAPI - Core impl")
class CoreEntityAPISpec extends Specification {

    @Unroll("#test")
    def "Constructor - success"() {
        when:
        def api = new CoreEntityAPI(context as EntityContext)
        then:
        api != null
        where:
        test   | context
        "core" | Mock(EntityContext, additionalInterfaces: [CoreContext])
    }

    @PendingFeature(
            reason = "Should only accept CoreEntityContext"
    )
    @Unroll("#test")
    def "Constructor - fails"() {
        when:
        new CoreEntityAPI(context as EntityContext)
        then:
        thrown(Exception)
        where:
        test      | context
        "null"    | null
        "generic" | Mock(EntityContext)
    }

}
