package latibro.automation.interfacebox

import latibro.automation.AutomationMod
import latibro.automation.core.api.APIHost
import latibro.automation.core.lua.LuaObjectProxy
import latibro.automation.integration.minecraft.api.entity.EntityAPI
import net.minecraft.init.Bootstrap
import net.minecraftforge.fml.common.DummyModContainer
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.ModMetadata
import org.apache.logging.log4j.Logger
import spock.lang.Specification

class InterfaceBoxAPIImplSpec extends Specification {

    def setupSpec() {
        Loader.instance()
        Bootstrap.register()
        Loader.instance().setupTestHarness(new DummyModContainer(new ModMetadata() {
            {
                modId = "test"
            }
        }))
        AutomationMod.logger = Mock(Logger.class)
    }

    // ***** LuaMethods

    def "LuaMethods"() {
        given:
        def api = new InterfaceBoxAPIImpl(Mock(APIHost))
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
        when:
        def interfaceBox = new InterfaceBoxAPIImpl(Mock(APIHost))
        then:
        interfaceBox instanceof InterfaceBoxAPI
    }

    // ***** getAllNameOfAPIAsString

    def "getAllNameOfAPIAsString - only build-in mod"() {
        given:
        def interfaceBox = new InterfaceBoxAPIImpl(Mock(APIHost))
        when:
        def apis = interfaceBox.getAllNameOfAPIAsString()
        then:
        apis == ["entity"] as Set
    }

    def "getAllNameOfAPIAsString - immersiverailroading installed"() {
        given:
        Loader.instance().setupTestHarness(new DummyModContainer(new ModMetadata() {
            {
                modId = "immersiverailroading"
            }
        }))
        def interfaceBox = new InterfaceBoxAPIImpl(Mock(APIHost))
        when:
        def apis = interfaceBox.getAllNameOfAPIAsString()
        then:
        apis.contains("rolling_stock")
    }

    // ***** initiateAPI

    def "getAPIByName - unknown - fails"() {
        given:
        def interfaceBox = new InterfaceBoxAPIImpl(Mock(APIHost))
        when:
        interfaceBox.getAPIByName()
        then:
        thrown(IllegalArgumentException)
    }

    def "getAPIByName - entity - return EntityAPI"() {
        given:
        def interfaceBox = new InterfaceBoxAPIImpl(Mock(APIHost))
        when:
        def api = interfaceBox.getAPIByName("entity")
        then:
        api instanceof EntityAPI
    }

}
