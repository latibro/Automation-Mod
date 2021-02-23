package latibro.automation.core.api.entity


import latibro.automation.core.context.entity.EntityLinkContext
import latibro.automation.nativeimpl.context.entity.CoreEntityLinkContext
import spock.lang.PendingFeature
import spock.lang.Specification
import spock.lang.Title
import spock.lang.Unroll

@Title("EntityLinkAPI - Core impl")
class CoreEntityLinkAPISpec extends Specification {

    @Unroll("#test")
    def "Constructor - success"() {
        when:
        def api = new CoreEntityLinkAPI(context as EntityLinkContext)
        then:
        api != null
        where:
        test               | context
        "core entity link" | Mock(CoreEntityLinkContext)
    }

    @PendingFeature(
            reason = "Should only accept CoreEntityContext"
    )
    @Unroll("#test")
    def "Constructor - fails"() {
        when:
        new CoreEntityLinkAPI(context as EntityLinkContext)
        then:
        thrown(Exception)
        where:
        test                  | context
        "null"                | null
        "generic entity link" | Mock(EntityLinkContext)
    }

}
