package latibro.automation.computer.integration.opencomputers

import latibro.automation.AutomationMod
import latibro.automation.core.lua.LuaObjectProxy
import latibro.automation.integration.opencomputers.OpenComputersObjectProxy
import org.apache.logging.log4j.Logger
import spock.lang.Specification
import spock.lang.Unroll

class OpenComputersObjectsSpec extends Specification {

    static Object SAFE_OC_OBJECT = "Hello world"
    static Object UNSAFE_OC_OBJECT = {}

    def setupSpec() {
        AutomationMod.logger = Mock(Logger.class)
    }

    // ***** isSafeOpenComputersObject

    @Unroll
    def "isSafeOpenComputersObject - #name - #expected"() {
        when:
        def output = OpenComputersObjects.isSafeOpenComputersObject(input)
        then:
        output == expected
        where:
        name                                    | input                                                    || expected
        "SAFE_OC_OBJECT"                        | SAFE_OC_OBJECT                                           || true
        "UNSAFE_OC_OBJECT"                      | UNSAFE_OC_OBJECT                                         || false
        "null"                                  | null                                                     || true
        "Boolean"                               | new Boolean(true)                                        || true
        "String"                                | "Hello world"                                            || true
        "Double"                                | new Double(123.4)                                        || true
        "Integer"                               | new Integer(123)                                         || false
        "Map"                                   | (Map) [first: "1"]                                       || true
        "OpenComputersObjectProxy"              | new OpenComputersObjectProxy(new LuaObjectProxy("test")) || true
        "random object"                         | {}                                                       || false
        "LuaObjectProxy"                        | new LuaObjectProxy("test")                               || false
        "List"                                  | (List) ["test"]                                          || false
        "array"                                 | (Object[]) ["test"]                                      || false
        "Collection"                            | (Collection) new HashSet(["test"])                       || false
        "Map with safe nested map"              | (Map) [first: (Map) [test: "hello"]]                     || true
        "Map with nested map with unsafe value" | (Map) [first: (Map) [test: UNSAFE_OC_OBJECT]]            || false
        "Map with nested map with unsafe key"   | (Map) [first: (Map) [(UNSAFE_OC_OBJECT): "hello"]]       || false
    }

    // ***** toOpenComputersObject

    def "toOpenComputersObject - safe OC object - return same"() {
        given:
        def input = SAFE_OC_OBJECT
        when:
        def output = OpenComputersObjects.toOpenComputersObject(input)
        then:
        output == input
    }

    def "toOpenComputersObject - unsafe OC object - fails"() {
        given:
        def input = UNSAFE_OC_OBJECT
        when:
        def output = OpenComputersObjects.toOpenComputersObject(input)
        then:
        thrown(ClassCastException)
    }

    def "toOpenComputersObject - LuaObjectProxy - wrap with OpenComputersObjectProxy"() {
        given:
        def input = new LuaObjectProxy("Hello world")
        when:
        def output = OpenComputersObjects.toOpenComputersObject(input)
        then:
        output instanceof OpenComputersObjectProxy
        def proxy = (OpenComputersObjectProxy) output
        proxy.getSource() == input
    }

    def "toOpenComputersObject - safe Map - return Map"() {
        given:
        def input = (Map) [first: "1", second: "2", third: "3"]
        when:
        def output = OpenComputersObjects.toOpenComputersObject(input)
        then:
        output instanceof Map
        output == input
    }

    def "toOpenComputersObject - safe Map - transforms keys"() {
        given:
        def input = (Map) [(new LuaObjectProxy("Hello world")): "test"]
        when:
        def output = OpenComputersObjects.toOpenComputersObject(input)
        then:
        output instanceof Map
        def map = (Map) output
        map.keySet()[0] instanceof OpenComputersObjectProxy
    }

    def "toOpenComputersObject - safe Map - transforms values"() {
        given:
        def input = (Map) [test: new LuaObjectProxy("Hello world")]
        when:
        def output = OpenComputersObjects.toOpenComputersObject(input)
        then:
        output instanceof Map
        def map = (Map) output
        map.get("test") instanceof OpenComputersObjectProxy
    }

    def "toOpenComputersObject - Map with unsafe value - fails"() {
        given:
        def input = (Map) [test: (UNSAFE_OC_OBJECT)]
        when:
        OpenComputersObjects.toOpenComputersObject(input)
        then:
        thrown(ClassCastException)
    }

    def "toOpenComputersObject - Map with unsafe key - fails"() {
        given:
        def input = (Map) [(UNSAFE_OC_OBJECT): "test"]
        when:
        OpenComputersObjects.toOpenComputersObject(input)
        then:
        thrown(ClassCastException)
    }

    def "toOpenComputersObject - Map with safe nested Map - return map"() {
        given:
        def input = (Map) [first: (Map) [test: "hello"]]
        when:
        def output = OpenComputersObjects.toOpenComputersObject(input)
        then:
        output instanceof Map
        output == input
    }

    def "toOpenComputersObject - Map with nested Map - transform nested map"() {
        given:
        def input = (Map) [first: (Map) [test: new LuaObjectProxy("hello")]]
        when:
        def output = OpenComputersObjects.toOpenComputersObject(input)
        then:
        output instanceof Map
        !OpenComputersObjects.isSafeOpenComputersObject(input)
        OpenComputersObjects.isSafeOpenComputersObject(output)
    }

    def "toOpenComputersObject - Map with nested unsafe map - fails"() {
        given:
        def input = (Map) [first: (Map) [test: UNSAFE_OC_OBJECT]]
        when:
        OpenComputersObjects.toOpenComputersObject(input)
        then:
        thrown(ClassCastException)
    }

    def "toOpenComputersObject - random object - fails"() {
        given:
        def input = {}
        when:
        OpenComputersObjects.toOpenComputersObject(input)
        then:
        thrown(ClassCastException)
    }

    // ***** fromOpenComputersObject

    def "fromOpenComputersObject - null - return null"() {
        given:
        def input = null
        when:
        def output = OpenComputersObjects.fromOpenComputersObject(input)
        then:
        output == null
    }

    def "fromOpenComputersObject - Number - return same"() {
        given:
        def input = 123
        when:
        def output = OpenComputersObjects.fromOpenComputersObject(input)
        then:
        output == input
    }

    def "fromOpenComputersObject - String - return same"() {
        given:
        def input = "hello world"
        when:
        def output = OpenComputersObjects.fromOpenComputersObject(input)
        then:
        output == input
    }

    def "fromOpenComputersObject - Boolean - return same"() {
        given:
        def input = true
        when:
        def output = OpenComputersObjects.fromOpenComputersObject(input)
        then:
        output == input
    }

    def "fromOpenComputersObject - OpenComputersObjectProxy - return source of proxy"() {
        given:
        def input = new OpenComputersObjectProxy(new LuaObjectProxy("hello world"))
        when:
        def output = OpenComputersObjects.fromOpenComputersObject(input)
        then:
        output instanceof LuaObjectProxy
        output == input.getSource()
    }

    def "fromOpenComputersObject - Map - transform from lua table (Map)"() {
        given:
        def input = (Map) [first: new OpenComputersObjectProxy(new LuaObjectProxy("hello world"))]
        when:
        def output = OpenComputersObjects.fromOpenComputersObject(input)
        then:
        output instanceof Map
        def map = (Map) output
        map.size() == 1
        map.get("first") instanceof LuaObjectProxy
    }

    def "fromOpenComputersObject - random object - fail"() {
        given:
        def input = {}
        when:
        OpenComputersObjects.fromOpenComputersObject(input)
        then:
        thrown(ClassCastException)
    }

    def "fromOpenComputersObject - array-like map (without n entry) - transforms to safe array map"() {
        given:
        def input = (Map) [1d: "first", 2d: "second"]
        def spy = GroovySpy(OpenComputersObjects, global: true)
        // Faking that input is an instance of OC build-in object
        OpenComputersObjects.isInstanceOfOCBuildInObject({ input.is(it) }) >> true
        when:
        def output = OpenComputersObjects.fromOpenComputersObject(input)
        then:
        output instanceof Map
        output == [1d: "first", 2d: "second"]
        !input.is(output)
        //1 * spy.toListLikeMap(_) //TODO check that toListLikeMap hasAPIFor been called
    }

    def "fromOpenComputersObject - array-like map (with n entry) - transforms to safe array map (without n entry)"() {
        given:
        def input = (Map) [1d: "first", 2d: "second", "n": 2d]
        def spy = GroovySpy(OpenComputersObjects, global: true)
        // Faking that input is an instance of OC build-in object
        OpenComputersObjects.isInstanceOfOCBuildInObject({ input.is(it) }) >> true
        when:
        def output = OpenComputersObjects.fromOpenComputersObject(input)
        then:
        output instanceof Map
        output == [1d: "first", 2d: "second"]
        !input.is(output)
        //1 * spy.toListLikeMap(_) //TODO check that toListLikeMap hasAPIFor been called
    }

    def "fromOpenComputersObject - array-like map (with n entry wrong number) - normal map (keeps n entry)"() {
        given:
        def input = (Map) [1d: "first", 2d: "second", "n": 10d]
        def spy = GroovySpy(OpenComputersObjects, global: true)
        // Faking that input is an instance of OC build-in object
        OpenComputersObjects.isInstanceOfOCBuildInObject({ input.is(it) }) >> true
        when:
        def output = OpenComputersObjects.fromOpenComputersObject(input)
        then:
        output == input
    }

}
