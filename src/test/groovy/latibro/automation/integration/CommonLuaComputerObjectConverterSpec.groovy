package latibro.automation.integration

import latibro.automation.AutomationMod
import spock.lang.Specification
import org.apache.logging.log4j.Logger


class CommonLuaComputerObjectConverterSpec extends Specification {

    def converter

    def setupSpec() {
        AutomationMod.logger = Mock(Logger.class)
    }

    def setup() {
        converter = Spy(CommonLuaComputerObjectConverter)
    }

    // ***** wrapObject

    def "wrapObject - Null - No need to do anything"() {
        given:
        def input = null
        when:
        def output = converter.wrapObject(input)
        then:
        output == null
    }

    def "wrapObject - Already wrapped - Passthrough"() {
        given:
        def input = new CommonLuaComputerObjectWrapper()
        when:
        def output = converter.wrapObject(input)
        then:
        output == input
    }

    def "wrapObject - Primitive (String) - Passthrough"() {
        given:
        def input = "hello world"
        when:
        def output = converter.wrapObject(input)
        then:
        output == input
    }

    def "wrapObject - Primitive (Boolean) - Passthrough"() {
        given:
        def input = Boolean.valueOf(true)
        when:
        def output = converter.wrapObject(input)
        then:
        output == input
    }

    def "wrapObject - Primitive (Number) - Passthrough"() {
        given:
        def input = new Integer(123)
        when:
        def output = converter.wrapObject(input)
        then:
        output == input
    }

    def "wrapObject - Array - return Array"() {
        given:
        def input = (Object[]) ["first", "second", "third"]
        when:
        def output = converter.wrapObject(input)
        then:
        output instanceof Object[]
        output == input
    }

    def "wrapObject - Array - wrap each element"() {
        given:
        def input = (Object[]) ["first", "second", "third"]
        when:
        converter.wrapObject(input)
        then:
        1 * converter.wrapObject("first")
        1 * converter.wrapObject("second")
        1 * converter.wrapObject("third")
    }

    def "wrapObject - Collection - return Array"() {
        given:
        def input = (Collection) ["first", "second", "third"]
        when:
        def output = converter.wrapObject(input)
        then:
        output instanceof Object[]
        output == input
    }

    def "wrapObject - Collection - wrap each element"() {
        given:
        def input = (Collection) ["first", "second", "third"]
        when:
        converter.wrapObject(input)
        then:
        1 * converter.wrapObject("first")
        1 * converter.wrapObject("second")
        1 * converter.wrapObject("third")
    }

    def "wrapObject - Map - return Map"() {
        given:
        def input = (Map) [first: "1", second: "2", third: "3"]
        when:
        def output = converter.wrapObject(input)
        then:
        output instanceof Map
        output == input
    }

    def "wrapObject - Map - wrap each key"() {
        given:
        def input = (Map) [first: "1", second: "2", third: "3"]
        when:
        converter.wrapObject(input)
        then:
        1 * converter.wrapObject("first")
        1 * converter.wrapObject("second")
        1 * converter.wrapObject("third")
    }

    def "wrapObject - Map - wrap each value"() {
        given:
        def input = (Map) [first: "1", second: "2", third: "3"]
        when:
        converter.wrapObject(input)
        then:
        1 * converter.wrapObject("1")
        1 * converter.wrapObject("2")
        1 * converter.wrapObject("3")
    }

    def "wrapObject - Object - return wrapped"() {
        given:
        def input = {}
        when:
        def output = converter.wrapObject(input)
        then:
        output instanceof CommonLuaComputerObjectWrapper
        ((CommonLuaComputerObjectWrapper) output).getWrappedObject() == input
    }

    // ***** wrapObject

    def "unwrapObject - Null - No need to do anything"() {
        given:
        def input = null
        when:
        def output = converter.unwrapObject(input)
        then:
        output == null
    }

    def "unwrapObject - Wrapped - Unwrapping"() {
        given:
        def input = new CommonLuaComputerObjectWrapper("hello world")
        when:
        def output = converter.unwrapObject(input)
        then:
        output instanceof String
        output == "hello world"
    }

    def "unwrapObject - Array - return Array"() {
        given:
        def input = (Object[]) ["first", "second", "third"]
        when:
        def output = converter.unwrapObject(input)
        then:
        output instanceof Object[]
        output == input
    }

    def "unwrapObject - Array - unwrap every element"() {
        given:
        def input = (Object[]) ["first", "second", "third"]
        when:
        Object output = converter.unwrapObject(input)
        then:
        1 * converter.unwrapObject("first")
        1 * converter.unwrapObject("second")
        1 * converter.unwrapObject("third")
    }

    def "unwrapObject - Unhandled pass-through"() {
        given:
        def input = {}
        when:
        def output = converter.unwrapObject(input)
        then:
        output == input
    }


}
