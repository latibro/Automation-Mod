package latibro.automation.integration.computercraft

import latibro.automation.AutomationMod
import latibro.automation.integration.CommonLuaComputerObjectWrapper
import org.apache.logging.log4j.Logger
import spock.lang.Specification

class ComputerCraftObjectConverterSpec extends Specification {

    def converter

    def setupSpec() {
        AutomationMod.logger = Mock(Logger.class)
    }

    def setup() {
        converter = Spy(ComputerCraftObjectConverter)
    }

    // ***** wrapObject

    def "wrapObject - not wrapped"() {
        given:
        Object input = "hello world"
        when:
        Object output = converter.wrapObject(input)
        then:
        output == input
    }

    def "wrapObject - wrapped"() {
        given:
        Object input = {}
        when:
        Object output = converter.wrapObject(input)
        then: "output is CC wrapped object"
        output instanceof ComputerCraftWrappedObject
        then: "CC wrapper holds common wrapper"
        def wrappedObject = ((ComputerCraftWrappedObject) output).getWrappedObject()
        wrappedObject instanceof CommonLuaComputerObjectWrapper
        then: "Common wrapper holds input"
        def wrappedWrappedObject = ((CommonLuaComputerObjectWrapper) wrappedObject).getWrappedObject()
        wrappedWrappedObject == input
    }

    def "wrapObject - wrapped in array"() {
        given:
        Object input = [{}, "hest"]
        when:
        Object output = converter.wrapObject(input)
        then:
        output instanceof Object[]
        output[0] instanceof ComputerCraftWrappedObject
        output[1] == "hest"
    }

    // ***** unwrapObject

    def "unwrapObject - not wrapped"() {
        given:
        Object input = "hello world"
        when:
        Object output = converter.unwrapObject(input)
        then:
        output == input
    }

    def "unwrapObject - wrapped"() {
        given:
        Object input = new ComputerCraftWrappedObject(new CommonLuaComputerObjectWrapper("hello world"))
        when:
        Object output = converter.unwrapObject(input)
        then:
        output == "hello world"
    }

}
