package latibro.automation.interfacebox

import latibro.automation.AutomationMod
import latibro.automation.core.lua.LuaObjectProxy
import latibro.automation.integration.minecraft.api.entity.EntityAPI
import latibro.automation.integration.minecraft.api.entity.EntityAPIImpl
import net.minecraft.world.World
import org.apache.logging.log4j.Logger
import spock.lang.Specification

class InterfaceBoxAPIImplTest extends Specification {

    def setupSpec() {
        AutomationMod.logger = Mock(Logger.class)
    }

    // ***** LuaMethods

    def "LuaMethods"() {
        given:
        def api = new InterfaceBoxAPIImpl(Mock(World))
        def proxy = new LuaObjectProxy(api)
        when:
        def methodNames = proxy.getMethodNames()
        then:
        methodNames.sort() == ["getAPI", "listAPI"].sort()
    }

    // ***** constructor

    def "constructor - null - fails"() {
        when:
        new InterfaceBoxAPIImpl(null)
        then:
        thrown(NullPointerException)
    }

    def "constructor - world - success"() {
        given:
        def world = Mock(World)
        when:
        def interfaceBox = new InterfaceBoxAPIImpl(world)
        then:
        interfaceBox instanceof InterfaceBoxAPI
    }

    // ***** initiateAPI

    def "findAllAvailableAPIAsStrings"() {
        given:
        def interfaceBox = new InterfaceBoxAPIImpl(Mock(World))
        when:
        def apis = interfaceBox.findAllAvailableAPIAsStrings()
        then:
        apis == ["entity"]
    }

    // ***** initiateAPI

    def "initiateAPI - unknown - fails"() {
        given:
        def interfaceBox = new InterfaceBoxAPIImpl(Mock(World))
        when:
        interfaceBox.getAPI()
        then:
        thrown(IllegalArgumentException)
    }

    def "initiateAPI - entity - EntityContext with world"() {
        given:
        GroovySpy(EntityAPIImpl, global: true)
        def world = Mock(World)
        def interfaceBox = new InterfaceBoxAPIImpl(world)
        when:
        def api = interfaceBox.getAPI("entity")
        then:
        api instanceof EntityAPI
        1 * new EntityAPIImpl(world)
    }

}
