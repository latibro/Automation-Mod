package latibro.automation.computer.integration.computercraft

import latibro.automation.AutomationMod
import latibro.automation.core.lua.LuaObjectProxy
import latibro.automation.integration.computercraft.ComputerCraftObjectProxy
import org.apache.logging.log4j.Logger
import spock.lang.Specification
import spock.lang.Unroll

class ComputerCraftObjectsSpec extends Specification {

    static Object SAFE_CC_OBJECT = "Hello world"
    static Object UNSAFE_CC_OBJECT = {}

    def setupSpec() {
        AutomationMod.logger = Mock(Logger.class)
    }

    // ***** isSafeComputerCraftObject

    @Unroll
    def "isSafeComputerCraftObject - #name - #expected"() {
        when:
        def output = ComputerCraftObjects.isSafeComputerCraftObject(input)
        then:
        output == expected
        where:
        name                                    | input                                                    || expected
        "SAFE_CC_OBJECT"                        | SAFE_CC_OBJECT                                           || true
        "UNSAFE_CC_OBJECT"                      | UNSAFE_CC_OBJECT                                         || false
        "null"                                  | null                                                     || true
        "Boolean"                               | new Boolean(true)                                        || true
        "String"                                | "Hello world"                                            || true
        "Double"                                | new Double(123.4)                                        || true
        "Integer"                               | new Integer(123)                                         || false
        "Map"                                   | (Map) [first: "1"]                                       || true
        "ComputerCraftObjectProxy"              | new ComputerCraftObjectProxy(new LuaObjectProxy("test")) || true
        "random object"                         | {}                                                       || false
        "LuaObjectProxy"                        | new LuaObjectProxy("test")                               || false
        "List"                                  | (List) ["test"]                                          || false
        "array"                                 | (Object[]) ["test"]                                      || false
        "Collection"                            | (Collection) new HashSet(["test"])                       || false
        "Map with safe nested map"              | (Map) [first: (Map) [test: "hello"]]                     || true
        "Map with nested map with unsafe value" | (Map) [first: (Map) [test: UNSAFE_CC_OBJECT]]            || false
        "Map with nested map with unsafe key"   | (Map) [first: (Map) [(UNSAFE_CC_OBJECT): "hello"]]       || false
    }

    // ***** toComputerCraftObject

    def "toComputerCraftObject - safe CC object - return same"() {
        given:
        def input = SAFE_CC_OBJECT
        when:
        def output = ComputerCraftObjects.toComputerCraftObject(input)
        then:
        output == input
    }

    def "toComputerCraftObject - unsafe CC object - fails"() {
        given:
        def input = UNSAFE_CC_OBJECT
        when:
        def output = ComputerCraftObjects.toComputerCraftObject(input)
        then:
        thrown(ClassCastException)
    }

    def "toComputerCraftObject - LuaObjectProxy - wrap with ComputerCraftObjectProxy"() {
        given:
        def input = new LuaObjectProxy("Hello world")
        when:
        def output = ComputerCraftObjects.toComputerCraftObject(input)
        then:
        output instanceof ComputerCraftObjectProxy
        def proxy = (ComputerCraftObjectProxy) output
        proxy.getSource() == input
    }

    def "toComputerCraftObject - safe Map - return Map"() {
        given:
        def input = (Map) [first: "1", second: "2", third: "3"]
        when:
        def output = ComputerCraftObjects.toComputerCraftObject(input)
        then:
        output instanceof Map
        output == input
    }

    def "toComputerCraftObject - safe Map - transforms keys"() {
        given:
        def input = (Map) [(new LuaObjectProxy("Hello world")): "test"]
        when:
        def output = ComputerCraftObjects.toComputerCraftObject(input)
        then:
        output instanceof Map
        def map = (Map) output
        map.keySet().getAt(0) instanceof ComputerCraftObjectProxy
    }

    def "toComputerCraftObject - safe Map - transforms values"() {
        given:
        def input = (Map) [test: new LuaObjectProxy("Hello world")]
        when:
        def output = ComputerCraftObjects.toComputerCraftObject(input)
        then:
        output instanceof Map
        def map = (Map) output
        map.get("test") instanceof ComputerCraftObjectProxy
    }

    def "toComputerCraftObject - Map with unsafe value - fails"() {
        given:
        def input = (Map) [test: (UNSAFE_CC_OBJECT)]
        when:
        ComputerCraftObjects.toComputerCraftObject(input)
        then:
        thrown(ClassCastException)
    }

    def "toComputerCraftObject - Map with unsafe key - fails"() {
        given:
        def input = (Map) [(UNSAFE_CC_OBJECT): "test"]
        when:
        ComputerCraftObjects.toComputerCraftObject(input)
        then:
        thrown(ClassCastException)
    }

    def "toComputerCraftObject - Map with safe nested Map - return map"() {
        given:
        def input = (Map) [first: (Map) [test: "hello"]]
        when:
        def output = ComputerCraftObjects.toComputerCraftObject(input)
        then:
        output instanceof Map
        output == input
    }

    def "toComputerCraftObject - Map with nested Map - transform nested map"() {
        given:
        def input = (Map) [first: (Map) [test: new LuaObjectProxy("hello")]]
        when:
        def output = ComputerCraftObjects.toComputerCraftObject(input)
        then:
        output instanceof Map
        !ComputerCraftObjects.isSafeComputerCraftObject(input)
        ComputerCraftObjects.isSafeComputerCraftObject(output)
    }

    def "toComputerCraftObject - Map with nested unsafe map - fails"() {
        given:
        def input = (Map) [first: (Map) [test: UNSAFE_CC_OBJECT]]
        when:
        ComputerCraftObjects.toComputerCraftObject(input)
        then:
        thrown(ClassCastException)
    }

    def "toComputerCraftObject - random object - fails"() {
        given:
        def input = {}
        when:
        ComputerCraftObjects.toComputerCraftObject(input)
        then:
        thrown(ClassCastException)
    }

    // ***** fromComputerCraftObject

    def "fromComputerCraftObject - null - return null"() {
        given:
        def input = null
        when:
        def output = ComputerCraftObjects.fromComputerCraftObject(input)
        then:
        output == null
    }

    def "fromComputerCraftObject - Number - return same"() {
        given:
        def input = 123
        when:
        def output = ComputerCraftObjects.fromComputerCraftObject(input)
        then:
        output == input
    }

    def "fromComputerCraftObject - String - return same"() {
        given:
        def input = "hello world"
        when:
        def output = ComputerCraftObjects.fromComputerCraftObject(input)
        then:
        output == input
    }

    def "fromComputerCraftObject - Boolean - return same"() {
        given:
        def input = true
        when:
        def output = ComputerCraftObjects.fromComputerCraftObject(input)
        then:
        output == input
    }

    def "fromComputerCraftObject - ComputerCraftObjectProxy - return source of proxy"() {
        given:
        def input = new ComputerCraftObjectProxy(new LuaObjectProxy("hello world"))
        when:
        def output = ComputerCraftObjects.fromComputerCraftObject(input)
        then:
        output instanceof LuaObjectProxy
        output == input.getSource()
    }

    def "fromComputerCraftObject - Map - transform from lua table (Map)"() {
        given:
        def input = (Map) [first: new ComputerCraftObjectProxy(new LuaObjectProxy("hello world"))]
        when:
        def output = ComputerCraftObjects.fromComputerCraftObject(input)
        then:
        output instanceof Map
        def map = (Map) output
        map.size() == 1
        map.get("first") instanceof LuaObjectProxy
    }

    def "fromComputerCraftObject - random object - fail"() {
        given:
        def input = {}
        when:
        def output = ComputerCraftObjects.fromComputerCraftObject(input)
        then:
        thrown(ClassCastException)
    }

}
