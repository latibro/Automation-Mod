package latibro.automation.integration.opencomputers

import latibro.automation.AutomationMod
import latibro.automation.core.lua.LuaObjectProxy
import li.cil.oc.api.machine.Arguments
import org.apache.logging.log4j.Logger
import spock.lang.Specification

class OpenComputersObjectProxySpec extends Specification {

    static LuaObjectProxy DUMMY_LUA_OBJECT_PROXY = new LuaObjectProxy("hello")
    static OpenComputersObjectProxy DUMMY_OPENCOMPUTERS_OBJECT_PROXY = new OpenComputersObjectProxy(DUMMY_LUA_OBJECT_PROXY)

    def setupSpec() {
        AutomationMod.logger = Mock(Logger.class)
    }

    // ***** constructor

    def "constructor - LuaObjectProxy - success"() {
        when:
        new OpenComputersObjectProxy(new LuaObjectProxy({ }))
        then:
        noExceptionThrown()
    }

    def "constructor - null - fails"() {
        when:
        new OpenComputersObjectProxy(null)
        then:
        thrown(NullPointerException)
    }

    // ***** getSource

    def "getSource - return original object"() {
        given:
        def source = new LuaObjectProxy("hello")
        when:
        def proxy = new OpenComputersObjectProxy(source)
        then:
        proxy.getSource() == source
    }

    // ***** methods

    def "methods - returns list from source"() {
        given:
        def source = Mock(LuaObjectProxy) {
            getMethodNames() >> (String[]) ["firstMethod", "secondMethod"]
        }
        def proxy = new OpenComputersObjectProxy(source)
        when:
        def methods = proxy.methods()
        then:
        methods == source.getMethodNames()
    }

    // ***** invoke

    def "invoke - method name - passed as method name"() {
        given:
        def source = Mock(LuaObjectProxy)
        def proxy = new OpenComputersObjectProxy(source)
        when:
        proxy.invoke("testMethod", null, null)
        then:
        1 * source.callMethod("testMethod", _)
    }

    def "invoke - null as arguments - pass null as argument"() {
        given:
        def source = Mock(LuaObjectProxy)
        def proxy = new OpenComputersObjectProxy(source)
        def arguments = null
        when:
        proxy.invoke("testMethod", null, arguments)
        then:
        1 * source.callMethod(_, null)
    }

    def "invoke - empty array as arguments - pass empty array as argument"() {
        given:
        def source = Mock(LuaObjectProxy)
        def proxy = new OpenComputersObjectProxy(source)
        def arguments = Mock(Arguments) {
            toArray() >> (Object[]) []
        }
        when:
        proxy.invoke("testMethod", null, arguments)
        then:
        1 * source.callMethod(_, (Object[]) [])
    }

    def "invoke - some arguments - pass arguments to method"() {
        given:
        def source = Mock(LuaObjectProxy)
        def proxy = new OpenComputersObjectProxy(source)
        def arguments = Mock(Arguments) {
            toArray() >> (Object[]) ["first", "second"]
        }
        when:
        proxy.invoke("testMethod", null, arguments)
        then:
        1 * source.callMethod(_, (Object[]) ["first", "second"])
    }

    def "invoke - OC specific argument - transforms argument before passing it to method"() {
        given:
        def source = Mock(LuaObjectProxy)
        def proxy = new OpenComputersObjectProxy(source)
        def arguments = Mock(Arguments) {
            toArray() >> (Object[]) [new OpenComputersObjectProxy(DUMMY_LUA_OBJECT_PROXY), "second"]
        }
        when:
        proxy.invoke("testMethod", null, arguments)
        then:
        1 * source.callMethod(_, (Object[]) [DUMMY_LUA_OBJECT_PROXY, "second"])
    }

    def "invoke - method return result - passed as return in array"() {
        given:
        def source = Mock(LuaObjectProxy) {
            callMethod(*_) >> "Hello world"
        }
        def proxy = new OpenComputersObjectProxy(source)
        when:
        def result = proxy.invoke("testMethod", null, null)
        then:
        result instanceof Object[]
        def array = (Object[]) result
        array.length == 1
        array[0] == "Hello world"
    }

    def "invoke - method returns OC unsafe object - transforms result before returning it"() {
        given:
        def source = Mock(LuaObjectProxy) {
            callMethod(*_) >> DUMMY_LUA_OBJECT_PROXY
        }
        def proxy = new OpenComputersObjectProxy(source)
        when:
        def result = proxy.invoke("testMethod", null, null)
        then:
        result instanceof Object[]
        def array = (Object[]) result
        array.length == 1
        OpenComputersObjects.isSafeOpenComputersObject(array[0])
        array[0] instanceof OpenComputersObjectProxy
    }

    def "callMethod - method throws exception - throw LuaException"() {
        given:
        def source = Mock(LuaObjectProxy) {
            callMethod(*_) >> { throw new Exception() }
        }
        def proxy = new OpenComputersObjectProxy(source)
        when:
        proxy.invoke("testMethod", null, null)
        then:
        thrown(Exception)
    }

}
