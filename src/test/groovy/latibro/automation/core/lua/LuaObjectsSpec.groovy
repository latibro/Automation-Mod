package latibro.automation.core.lua

import latibro.automation.AutomationMod
import org.apache.logging.log4j.Logger
import spock.lang.Specification
import spock.lang.Unroll

class LuaObjectsSpec extends Specification {

    static Object SAFE_LUA_OBJECT = "Hello world"
    static Object UNSAFE_LUA_OBJECT = {}

    def setupSpec() {
        AutomationMod.logger = Mock(Logger.class)
    }

    // ***** isSafeLuaObject

    @Unroll
    def "isSafeLuaObject - #name - #expected"() {
        when:
        def output = LuaObjects.isSafeLuaObject(input)
        then:
        output == expected
        where:
        name                | input                              || expected
        "SAFE_LUA_OBJECT"   | SAFE_LUA_OBJECT                    || true
        "UNSAFE_LUA_OBJECT" | UNSAFE_LUA_OBJECT                  || false
        "SAFE_LUA_TABLE"    | LuaTablesSpec.SAFE_LUA_TABLE       || true
        "UNSAFE_LUA_TABLE"  | LuaTablesSpec.UNSAFE_LUA_TABLE     || false
        "null"              | null                               || true
        "Boolean"           | new Boolean(true)                  || true
        "String"            | "Hello world"                      || true
        "Double"            | new Double(123.4)                  || true
        "Integer"           | new Integer(123)                   || false
        "Map"               | (Map) [first: "1"]                 || true
        "LuaObjectProxy"    | new LuaObjectProxy("test")         || true
        "random object"     | {}                                 || false
        "List"              | (List) ["test"]                    || false
        "array"             | (Object[]) ["test"]                || false
        "Collection"        | (Collection) new HashSet(["test"]) || false
    }

    // ***** toLuaObject

    def "toLuaObject - null - return null"() {
        given:
        def input = null
        when:
        def output = LuaObjects.toLuaObject(input)
        then:
        output == null
    }

    def "toLuaObject - LuaObjectProxy - return same"() {
        given:
        def input = new LuaObjectProxy({})
        when:
        def output = LuaObjects.toLuaObject(input)
        then:
        output == input
    }

    def "toLuaObject - String - return same"() {
        given:
        def input = "hello world"
        when:
        def output = LuaObjects.toLuaObject(input)
        then:
        output == input
    }

    def "toLuaObject - Boolean - return same"() {
        given:
        def input = Boolean.valueOf(true)
        when:
        def output = LuaObjects.toLuaObject(input)
        then:
        output == input
    }

    def "toLuaObject - Integer - transform to Double"() {
        given:
        def input = new Integer(123)
        when:
        def output = LuaObjects.toLuaObject(input)
        then:
        output instanceof Double
        output == 123
    }

    def "toLuaObject - Double - return same"() {
        given:
        def input = new Double(123.4)
        when:
        def output = LuaObjects.toLuaObject(input)
        then:
        output instanceof Double
        output == 123.4
    }

    def "toLuaObject - Array - transform to lua table (Map)"() {
        given:
        def input = (Object[]) ["first", "second", "third"]
        when:
        def output = LuaObjects.toLuaObject(input)
        then:
        output instanceof Map
        Map map = (Map) output
        map.size() == 3
        map.get(1d) == "first"
        map.get(2d) == "second"
        map.get(3d) == "third"
    }

    def "toLuaObject - Collection - transform to lua table (Map)"() {
        given:
        def input = (Collection) ["first", "second", "third"]
        when:
        def output = LuaObjects.toLuaObject(input)
        then:
        output instanceof Map
        Map map = (Map) output
        map.size() == 3
        map.containsValue("first")
        map.containsValue("second")
        map.containsValue("third")
    }

    def "toLuaObject - List - transform to lua table (Map)"() {
        given:
        def input = (List) ["first", "second", "third"]
        when:
        def output = LuaObjects.toLuaObject(input)
        then:
        output instanceof Map
        Map map = (Map) output
        map.size() == 3
        map.get(1d) == "first"
        map.get(2d) == "second"
        map.get(3d) == "third"
    }

    def "toLuaObject - Map - transform to lua table (Map)"() {
        given:
        def input = (Map) [first: "1", second: "2", third: "3"]
        when:
        def output = LuaObjects.toLuaObject(input)
        then:
        output instanceof Map
        Map map = (Map) output
        map.size() == 3
        map.get("first") == "1"
        map.get("second") == "2"
        map.get("third") == "3"
    }

    def "toLuaObject - Object - wrap in LuaObjectProxy"() {
        given:
        def input = {}
        when:
        def output = LuaObjects.toLuaObject(input)
        then:
        output instanceof LuaObjectProxy
        ((LuaObjectProxy) output).getSource() == input
    }

    // ***** fromLuaObject

    def "fromLuaObject - null - return null"() {
        given:
        def input = null
        when:
        def output = LuaObjects.fromLuaObject(input)
        then:
        output == null
    }

    def "fromLuaObject - Number - return same"() {
        given:
        def input = 123
        when:
        def output = LuaObjects.fromLuaObject(input)
        then:
        output == input
    }

    def "fromLuaObject - String - return same"() {
        given:
        def input = "hello world"
        when:
        def output = LuaObjects.fromLuaObject(input)
        then:
        output == input
    }

    def "fromLuaObject - Boolean - return same"() {
        given:
        def input = true
        when:
        def output = LuaObjects.fromLuaObject(input)
        then:
        output == input
    }

    def "fromLuaObject - LuaObjectProxy - return source of proxy"() {
        given:
        def input = new LuaObjectProxy("hello world")
        when:
        def output = LuaObjects.fromLuaObject(input)
        then:
        output instanceof String
        output == "hello world"
    }

    def "fromLuaObject - Map - transform from lua table (Map)"() {
        given:
        def input = (Map) [first: new LuaObjectProxy("hello")]
        when:
        def output = LuaObjects.fromLuaObject(input)
        then:
        output instanceof Map
        def map = (Map) output
        map.size() == 1
        map.get("first") == "hello"
    }

    def "fromLuaObject - Object - fail"() {
        given:
        def input = {}
        when:
        def output = LuaObjects.fromLuaObject(input)
        then:
        thrown(ClassCastException)
    }

}
