package latibro.automation.integration.computercraft

import dan200.computercraft.api.lua.LuaException
import latibro.automation.AutomationMod
import latibro.automation.integration.lua.LuaObjectProxy
import org.apache.logging.log4j.Logger
import spock.lang.Specification

class ComputerCraftObjectProxySpec extends Specification {

    static LuaObjectProxy DUMMY_LUA_OBJECT_PROXY = new LuaObjectProxy("hello")
    static ComputerCraftObjectProxy DUMMY_COMPUTERCRAFT_OBJECT_PROXY = new ComputerCraftObjectProxy(DUMMY_LUA_OBJECT_PROXY)

    def setupSpec() {
        AutomationMod.logger = Mock(Logger.class)
    }

    // ***** constructor

    def "constructor - LuaObjectProxy - success"() {
        when:
        new ComputerCraftObjectProxy(new LuaObjectProxy({ }))
        then:
        noExceptionThrown()
    }

    def "constructor - null - fails"() {
        when:
        new ComputerCraftObjectProxy(null)
        then:
        thrown(NullPointerException)
    }

    // ***** getSource

    def "getSource - return original object"() {
        given:
        def source = new LuaObjectProxy("hello")
        when:
        def proxy = new ComputerCraftObjectProxy(source)
        then:
        proxy.getSource() == source
    }

    // ***** getMethodNames

    def "getMethodNames - returns list from source"() {
        given:
        def source = Mock(LuaObjectProxy) {
            getMethodNames() >> (String[]) ["firstMethod", "secondMethod"]
        }
        def proxy = new ComputerCraftObjectProxy(source)
        when:
        def methods = proxy.getMethodNames()
        then:
        methods == source.getMethodNames()
    }

    // ***** callMethod

    def "callMethod - unknown method - fails"() {
        given:
        def source = Mock(LuaObjectProxy) {
            getMethodNames() >> (String[]) ["testMethod"]
        }
        def proxy = new ComputerCraftObjectProxy(source)
        when:
        proxy.callMethod(null,100, null)
        then:
        thrown(LuaException)
    }

    def "callMethod - found method - calls on source"() {
        given:
        def source = Mock(LuaObjectProxy) {
            getMethodNames() >> (String[]) ["testMethod"]
        }
        def proxy = new ComputerCraftObjectProxy(source)
        when:
        proxy.callMethod(null,0, null)
        then:
        1 * source.callMethod("testMethod", _)
    }

    def "callMethod - null as arguments - pass null as argument"() {
        given:
        def source = Mock(LuaObjectProxy) {
            getMethodNames() >> (String[]) ["testMethod"]
        }
        def proxy = new ComputerCraftObjectProxy(source)
        def arguments = null
        when:
        proxy.callMethod(null,0, arguments)
        then:
        1 * source.callMethod(_, null)
    }

    def "callMethod - empty array as arguments - pass empty array as argument"() {
        given:
        def source = Mock(LuaObjectProxy) {
            getMethodNames() >> (String[]) ["testMethod"]
        }
        def proxy = new ComputerCraftObjectProxy(source)
        def arguments = (Object[]) []
        when:
        proxy.callMethod(null,0, arguments)
        then:
        1 * source.callMethod(_, (Object[]) [])
    }

    def "callMethod - some arguments - pass arguments to method"() {
        given:
        def source = Mock(LuaObjectProxy) {
            getMethodNames() >> (String[]) ["testMethod"]
        }
        def proxy = new ComputerCraftObjectProxy(source)
        def arguments = (Object[]) ["first", "second"]
        when:
        proxy.callMethod(null,0, arguments)
        then:
        1 * source.callMethod(_, (Object[]) ["first", "second"])
    }

    def "callMethod - cc specific argument - transforms argument before passing it to method"() {
        given:
        def source = Mock(LuaObjectProxy) {
            getMethodNames() >> (String[]) ["testMethod"]
        }
        def proxy = new ComputerCraftObjectProxy(source)
        def arguments = (Object[]) [new ComputerCraftObjectProxy(DUMMY_LUA_OBJECT_PROXY), "second"]
        when:
        proxy.callMethod(null,0, arguments)
        then:
        1 * source.callMethod(_, (Object[]) [DUMMY_LUA_OBJECT_PROXY, "second"])
    }

    def "callMethod - method return result - passed as return in array"() {
        given:
        def source = Mock(LuaObjectProxy) {
            getMethodNames() >> (String[]) ["testMethod"]
            callMethod(*_) >> "Hello world"
        }
        def proxy = new ComputerCraftObjectProxy(source)
        when:
        def result = proxy.callMethod(null,0, null)
        then:
        result instanceof Object[]
        def array = (Object[]) result
        array.length == 1
        array[0] == "Hello world"
    }

    def "callMethod - method returns cc unsafe object - transforms result before returning it"() {
        given:
        def source = Mock(LuaObjectProxy) {
            getMethodNames() >> (String[]) ["testMethod"]
            callMethod(*_) >> DUMMY_LUA_OBJECT_PROXY
        }
        def proxy = new ComputerCraftObjectProxy(source)
        when:
        def result = proxy.callMethod(null,0, null)
        then:
        result instanceof Object[]
        def array = (Object[]) result
        array.length == 1
        ComputerCraftObjects.isComputerCraftSafe(array[0])
        array[0] instanceof ComputerCraftObjectProxy
    }

    def "callMethod - method throws exception - throw LuaException"() {
        given:
        def source = Mock(LuaObjectProxy) {
            getMethodNames() >> (String[]) ["testMethod"]
            callMethod(*_) >> { throw new Exception() }
        }
        def proxy = new ComputerCraftObjectProxy(source)
        when:
        proxy.callMethod(null,0, null)
        then:
        thrown(LuaException)
    }

}
