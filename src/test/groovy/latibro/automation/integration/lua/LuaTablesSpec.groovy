package latibro.automation.integration.lua

import latibro.automation.AutomationMod
import org.apache.logging.log4j.Logger
import spock.lang.Specification
import spock.lang.Unroll

class LuaTablesSpec extends Specification {

    static Map SAFE_LUA_TABLE = [test: LuaObjectsSpec.SAFE_LUA_OBJECT]
    static Map UNSAFE_LUA_TABLE = [test: LuaObjectsSpec.UNSAFE_LUA_OBJECT]

    def setupSpec() {
        AutomationMod.logger = Mock(Logger.class)
    }

    def setup() {
        GroovySpy(LuaTables, global: true)
    }

    // ***** isSafeLuaTable

    @Unroll
    def "isSafeLuaTable - #name - #expected"() {
        when:
        def output = LuaTables.isSafeLuaTable(input)
        then:
        output == expected
        where:
        name                            | input                                                                    || expected
        "SAFE_LUA_TABLE"                | SAFE_LUA_TABLE                                                           || true
        "UNSAFE_LUA_TABLE"              | UNSAFE_LUA_TABLE                                                         || false
        "null"                          | null                                                                     || true
        "Map with safe keys and values" | (Map) [first: "1", second: "2", third: "3"]                              || true
        "Map with unsafe key"           | (Map) [first: LuaObjectsSpec.UNSAFE_LUA_OBJECT, second: "2", third: "3"] || false
        "Map with unsafe value"         | (Map) [(LuaObjectsSpec.UNSAFE_LUA_OBJECT): "1", second: "2", third: "3"] || false
        "Map with safe nested Map"      | (Map) [first: SAFE_LUA_TABLE, second: "2", third: "3"]                   || true
        "Map with unsafe nested Map"    | (Map) [first: UNSAFE_LUA_TABLE, second: "2", third: "3"]                 || false
        "random object"                 | {}                                                                       || false
    }

    // ***** isLuaTableCandidate

    @Unroll
    def "isLuaTableCandidate - #name - #expected"() {
        when:
        def output = LuaTables.isLuaTableCandidate(input)
        then:
        output == expected
        where:
        name            | input                               || expected
        "Map"           | (Map) [first: "1"]                  || true
        "List"          | (List) ["first"]                    || true
        "array"         | (Object[]) ["first"]                || true
        "Collection"    | (Collection) new HashSet(["first"]) || true
        "random object" | {}                                  || false
    }

    // ***** toLuaTable

    def "toLuaTable - null - return null"() {
        given:
        def input = null
        when:
        def output = LuaTables.toLuaTable(input)
        then:
        output == null
    }

    def "toLuaTable - safe lua table (Map) - return same"() {
        given:
        def input = (Map) [first: "1", second: "2", third: "3"]
        when:
        def output = LuaTables.toLuaTable(input)
        then:
        output == input
    }

    def "toLuaTable - unsafe lua table (Map) - transform to safe lua table (Map)"() {
        given:
        def input = (Map) [first: LuaObjectsSpec.UNSAFE_LUA_OBJECT, second: "2", third: "3"]
        when:
        def output = LuaTables.toLuaTable(input)
        then:
        !LuaTables.isSafeLuaTable(input)
        LuaTables.isSafeLuaTable(output)
        Map map = (Map) output
        map.size() == 3
        LuaObjects.isSafeLuaObject(map.get("first"))
        map.get("second") == "2"
        map.get("third") == "3"
    }

    def "toLuaTable - Map with unsafe value - transform to safe lua value"() {
        given:
        def input = (Map) [test: (LuaObjectsSpec.UNSAFE_LUA_OBJECT)]
        when:
        def output = LuaTables.toLuaTable(input)
        then:
        !LuaObjects.isSafeLuaObject(input.get("test"))
        LuaObjects.isSafeLuaObject(((Map) output).get("test"))
    }

    def "toLuaTable - Map with unsafe key - transform to safe lua key"() {
        given:
        def input = (Map) [(LuaObjectsSpec.UNSAFE_LUA_OBJECT): "test"]
        when:
        def output = LuaTables.toLuaTable(input)
        then:
        !LuaObjects.isSafeLuaObject(input.keySet().getAt(0))
        LuaObjects.isSafeLuaObject(((Map) output).keySet().getAt(0))
    }

    def "toLuaTable - List - transform to safe lua table (Map)"() {
        given:
        def input = (List) ["first", "second", "third"]
        when:
        def output = LuaTables.toLuaTable(input)
        then:
        LuaTables.isSafeLuaTable(output)
        Map map = (Map) output
        map.size() == 3
        map.containsValue("first")
        map.containsValue("second")
        map.containsValue("third")
        map.get(1d) == "first"
        map.get(2d) == "second"
        map.get(3d) == "third"
    }

    def "toLuaTable - array - transform to safe lua table (Map)"() {
        given:
        def input = (Object[]) ["first", "second", "third"]
        when:
        def output = LuaTables.toLuaTable(input)
        then:
        LuaTables.isSafeLuaTable(output)
        Map map = (Map) output
        map.size() == 3
        map.containsValue("first")
        map.containsValue("second")
        map.containsValue("third")
        map.get(1d) == "first"
        map.get(2d) == "second"
        map.get(3d) == "third"
    }

    def "toLuaTable - Collection - transform to safe lua table (Map)"() {
        given:
        def input = (Collection) new HashSet(["first", "second", "third"])
        when:
        def output = LuaTables.toLuaTable(input)
        then:
        LuaTables.isSafeLuaTable(output)
        Map map = (Map) output
        map.size() == 3
        map.containsValue("first")
        map.containsValue("second")
        map.containsValue("third")
    }

    def "toLuaTable - random object - fails"() {
        given:
        def input = {}
        when:
        LuaTables.toLuaTable(input)
        then:
        thrown(ClassCastException)
    }

    // ***** fromLuaTable

    def "fromLuaTable - null - return null"() {
        given:
        def input = null
        when:
        def output = LuaTables.fromLuaTable(input)
        then:
        output == null
    }

    def "fromLuaTable - Map with no lua specific keys or values - return Map"() {
        given:
        def input = (Map) [first: "1", second: "2", third: "3"]
        when:
        def output = LuaTables.fromLuaTable(input)
        then:
        output instanceof Map
        output == input
    }

    def "fromLuaTable - Map with unsafe value - return Map"() {
        given:
        def input = (Map) [test: new LuaObjectProxy("Hello world")]
        when:
        def output = LuaTables.fromLuaTable(input)
        then:
        output == (Map) [test: "Hello world"]
    }

    def "fromLuaTable - Map with unsafe key - return Map"() {
        given:
        def input = (Map) [(new LuaObjectProxy("Hello world")): "test"]
        when:
        def output = LuaTables.fromLuaTable(input)
        then:
        output == (Map) ["Hello world": "test"]
    }

    def "fromLuaTable - random object - fails"() {
        given:
        def input = {}
        when:
        LuaTables.fromLuaTable(input)
        then:
        thrown(ClassCastException)
    }

    // ***** isList

    @Unroll
    def "isList - #name - #expected"() {
        when:
        def output = LuaTables.isList(input)
        then:
        output == expected
        where:
        name                         | input                                           || expected
        "null"                       | null                                            || false
        "empty Map"                  | (Map) [:]                                       || true
        "keys in sequence"           | (Map) [1d: "first", 2d: "second", 3d: "third"]  || true
        "keys out of sequence"       | (Map) [2d: "second", 1d: "first", 3d: "third"]  || true
        "keys with gaps in sequence" | (Map) [1d: "first", 2d: "second", 10d: "tenth"] || false
        "wrong key type"             | (Map) [1d: "first", test: "test"]               || false
    }

    // ***** toList

    @Unroll
    def "toList - #name - fail"() {
        when:
        LuaTables.toList(input)
        then:
        thrown(IllegalArgumentException)
        where:
        name                         | input
        "null"                       | null
        "keys with gaps in sequence" | (Map) [1d: "first", 2d: "second", 10d: "tenth"]
        "wrong key type"             | (Map) [1d: "first", test: "test"]
    }

    @Unroll
    def "toList - #name - return list"() {
        when:
        def output = LuaTables.toList(input)
        then:
        output == expected
        where:
        name                         | input                                           || expected
        "empty Map"                  | (Map) [:]                                       || (List) []
        "keys in sequence"           | (Map) [1d: "first", 2d: "second", 3d: "third"]  || (List) ["first", "second", "third"]
        "keys out of sequence"       | (Map) [2d: "second", 1d: "first", 3d: "third"]  || (List) ["first", "second", "third"]
    }

}
