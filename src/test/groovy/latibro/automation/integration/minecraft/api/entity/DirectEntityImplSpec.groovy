package latibro.automation.integration.minecraft.api.entity

import latibro.automation.core.lua.LuaObjectProxy
import spock.lang.Specification

class DirectEntityImplSpec extends Specification {

    def "LuaMethods"() {
        given:
        def api = Mock(DirectEntity)
        def proxy = new LuaObjectProxy(api)
        when:
        def methodNames = proxy.getMethodNames()
        then:
        methodNames.sort() == ["getUUID", "getType", "getPosition", "isAvailable"].sort()
    }

}