package latibro.automation.interfacebox

import latibro.automation.AutomationMod
import latibro.automation.core.context.tileentity.TileEntityContext
import latibro.automation.core.lua.LuaObjectProxy
import net.minecraft.init.Bootstrap
import net.minecraftforge.fml.common.DummyModContainer
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.ModMetadata
import org.apache.logging.log4j.Logger
import spock.lang.Ignore
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

    @Ignore
    def "LuaMethods"() {
        given:
        def api = new InterfaceBoxAPIImpl(Mock(TileEntityContext))
        def proxy = new LuaObjectProxy(api)
        when:
        def methodNames = proxy.getMethodNames()
        then:
        methodNames.sort() == ["findContextAPI", "listAPI"].sort()
    }

    // ***** constructor

    def "constructor - null - fails"() {
        when:
        new InterfaceBoxAPIImpl(null)
        then:
        thrown(NullPointerException)
    }

    @Ignore
    def "constructor - world - success"() {
        when:
        def interfaceBox = new InterfaceBoxAPIImpl(Mock(TileEntityContext))
        then:
        interfaceBox instanceof InterfaceBoxAPI
    }

    // ***** getAPINames


    // ***** initiateAPI

    @Ignore
    def "getAPIByName - unknown - fails"() {
        given:
        def interfaceBox = new InterfaceBoxAPIImpl(Mock(TileEntityContext))
        when:
        interfaceBox.getAPI("unknown")
        then:
        thrown(IllegalArgumentException)
    }

}
