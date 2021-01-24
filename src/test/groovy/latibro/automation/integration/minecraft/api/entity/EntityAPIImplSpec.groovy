package latibro.automation.integration.minecraft.api.entity

import latibro.automation.core.lua.LuaObjectProxy
import spock.lang.Specification

class EntityAPIImplSpec extends Specification {

    def "LuaMethods"() {
        given:
        def api = Mock(EntityAPI)
        def proxy = new LuaObjectProxy(api)
        when:
        def methodNames = proxy.getMethodNames()
        then:
        methodNames.sort() == ["isLoaded", "getUUID", "getType", "getPosition", "getFacing", "asAPI"].sort()
    }

}
