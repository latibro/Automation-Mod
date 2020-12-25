package latibro.automation.integration

import latibro.automation.AutomationMod
import org.apache.logging.log4j.Logger
import org.junit.jupiter.api.Nested
import spock.lang.Specification

class CommonLuaComputerObjectWrapperSpec extends Specification {

    def setupSpec() {
        AutomationMod.logger = Mock(Logger.class)
    }

    // ***** constructor

    def "constructor sucess with object as input"() {
        given:
        def original = { }
        when:
        def wrapper = new CommonLuaComputerObjectWrapper(original)
        then:
        wrapper.wrappedObject == original
    }

    def "constructor fails with null as input"() {
        given:
        def original = null
        when:
        new CommonLuaComputerObjectWrapper(original)
        then:
        thrown(Throwable)
    }

    // ***** getWrappedObject

    def "getWrappedObject return original object"() {
        given:
        def original = { }
        def wrapper = new CommonLuaComputerObjectWrapper(original)
        when:
        def wrapped = wrapper.getWrappedObject()
        then:
        wrapped == original
    }

    // ***** getMethodNames

    def "getMethodNames returns string array"() {
        given:
        def original = new Object() {}
        def wrapper = new CommonLuaComputerObjectWrapper(original)
        when:
        def methods = wrapper.getMethodNames()
        then:
        methods instanceof String[]
    }

    def "getMethodNames contains public method"() {
        given:
        def original = new Object() {
            public void publicMethod() {}
        }
        def wrapper = new CommonLuaComputerObjectWrapper(original)
        when:
        def methods = wrapper.getMethodNames()
        then:
        methods.contains("publicMethod")
    }

    def "getMethodNames does not contain private method"() {
        given:
        def original = new Object() {
            private void privateMethod() {}
        }
        def wrapper = new CommonLuaComputerObjectWrapper(original)
        when:
        def methods = wrapper.getMethodNames()
        then:
        !methods.contains("privateMethod")
    }

    def "getMethodNames only has method name once"() {
        given:
        def original = new Object() {
            public void publicMethod() {}
            public void publicMethod(def input) {}
        }
        def wrapper = new CommonLuaComputerObjectWrapper(original)
        when:
        def methods = wrapper.getMethodNames()
        then:
        methods.count("publicMethod") == 1
    }

    // ***** callMethod

    def "callMethod unknown method"() {
        given:
        def original = new Object() {
            public void testMethod() {}
        }
        def wrapper = new CommonLuaComputerObjectWrapper(original)
        when:
        wrapper.callMethod("anotherMethod", null)
        then:
        thrown(Throwable)
    }

    def "callMethod null as arguments"() {
        given:
        def original = Spy(new Object() {
            public void testMethod() {}
        })
        def wrapper = new CommonLuaComputerObjectWrapper(original)
        when:
        wrapper.callMethod("testMethod", null)
        then:
        1 * original.testMethod()
    }

    def "callMethod empty array as arguments"() {
        given:
        def original = Spy(new Object() {
            public void testMethod() {}
        })
        def wrapper = new CommonLuaComputerObjectWrapper(original)
        when:
        wrapper.callMethod("testMethod", [] as Object[])
        then:
        1 * original.testMethod()
    }

    def "callMethod passes arguments"() {
        given:
        def original = Spy(new Object() {
            public void testMethod(def arg1, def arg2, def arg3) {}
        })
        def wrapper = new CommonLuaComputerObjectWrapper(original)
        when:
        wrapper.callMethod("testMethod", ["first", "second", 3] as Object[])
        then:
        1 * original.testMethod("first", "second", 3)
    }

    def "callMethod too many arguments"() {
        given:
        def original = new Object() {
            public void testMethod(def arg1) {}
        }
        def wrapper = new CommonLuaComputerObjectWrapper(original)
        when:
        wrapper.callMethod("testMethod", ["first", "second"] as Object[])
        then:
        thrown(Throwable)
    }

    def "callMethod too few arguments"() {
        given:
        def original = new Object() {
            public void testMethod(def arg1, def arg2) {}
        }
        def wrapper = new CommonLuaComputerObjectWrapper(original)
        when:
        wrapper.callMethod("testMethod", ["first"] as Object[])
        then:
        thrown(Throwable)
    }

}
