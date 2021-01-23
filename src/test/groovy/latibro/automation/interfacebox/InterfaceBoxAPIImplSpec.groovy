package latibro.automation.interfacebox

import latibro.automation.AutomationMod
import latibro.automation.core.lua.LuaObjectProxy
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
        def api = new InterfaceBoxAPIImpl(Mock(InterfaceBoxTileEntity))
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
        def interfaceBox = new InterfaceBoxAPIImpl(Mock(InterfaceBoxTileEntity))
        then:
        interfaceBox instanceof InterfaceBoxAPI
    }

    // ***** getAllAPIAsNameString


    // ***** initiateAPI

    def "getAPIByName - unknown - fails"() {
        given:
        def interfaceBox = new InterfaceBoxAPIImpl(Mock(InterfaceBoxTileEntity))
        when:
        interfaceBox.getAPIByName("unknown")
        then:
        thrown(IllegalArgumentException)
    }

}
