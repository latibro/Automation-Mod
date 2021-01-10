package latibro.automation.core.lua

import latibro.automation.AutomationMod
import latibro.automation.api.core.lua.LuaMethod
import org.apache.logging.log4j.Logger
import spock.lang.Specification

class LuaObjectProxySpec extends Specification {

    def setupSpec() {
        AutomationMod.logger = Mock(Logger.class)
    }

    // ***** constructor

    def "constructor - random object - success"() {
        when:
        new LuaObjectProxy({ })
        then:
        noExceptionThrown()
    }

    def "constructor - null - fails"() {
        when:
        new LuaObjectProxy(null)
        then:
        thrown(NullPointerException)
    }

    // ***** getSource

    def "getSource - return original object"() {
        given:
        def source = { }
        when:
        def proxy = new LuaObjectProxy(source)
        then:
        proxy.getSource() == source
    }

    // ***** getMethodNames

    def "getMethodNames - returns string array"() {
        given:
        def proxy = new LuaObjectProxy({ })
        when:
        def methods = proxy.getMethodNames()
        then:
        methods instanceof String[]
    }

    def "getMethodNames - contains public method"() {
        given:
        def source = new Object() {
            @LuaMethod
            void publicMethod() {}
        }
        def proxy = new LuaObjectProxy(source)
        when:
        def methods = proxy.getMethodNames()
        then:
        methods.contains("publicMethod")
    }

    def "getMethodNames - only contains methods annotated with LuaMethod"() {
        given:
        def source = new Object() {
            @LuaMethod
            void luaMethod() {}

            void anotherMethod() {}
        }
        def proxy = new LuaObjectProxy(source)
        when:
        def methods = proxy.getMethodNames()
        then:
        methods.size() == 1
        methods.contains("luaMethod")
        !methods.contains("anotherMethod")
    }

    def "getMethodNames - LuaMethod with alias"() {
        given:
        def source = new Object() {
            @LuaMethod
            void luaMethod() {}

            @LuaMethod(name="aliasMethod")
            void anotherMethod() {}
        }
        def proxy = new LuaObjectProxy(source)
        when:
        def methods = proxy.getMethodNames()
        then:
        methods.size() == 2
        methods.contains("luaMethod")
        methods.contains("aliasMethod")
        !methods.contains("anotherMethod")
    }


    def "getMethodNames - does not contain private method"() {
        given:
        def source = new Object() {
            @LuaMethod
            private void privateMethod() {}
        }
        def proxy = new LuaObjectProxy(source)
        when:
        def methods = proxy.getMethodNames()
        then:
        !methods.contains("privateMethod")
    }

    def "getMethodNames - only has method name once"() {
        given:
        def source = new Object() {
            @LuaMethod
            void publicMethod() {}

            @LuaMethod
            void publicMethod(def input) {}
        }
        def proxy = new LuaObjectProxy(source)
        when:
        def methods = proxy.getMethodNames()
        then:
        methods.count("publicMethod") == 1
    }

    // ***** callMethod

    def "callMethod - unknown method - fails"() {
        given:
        def source = new Object() {
            @LuaMethod
            void testMethod() {}
        }
        def proxy = new LuaObjectProxy(source)
        when:
        proxy.callMethod("anotherMethod", null)
        then:
        thrown(NoSuchMethodException)
    }

    def "callMethod - found method - call method"() {
        given:
        def source = Spy(new Object() {
            @LuaMethod
            void testMethod() {}
        })
        def proxy = new LuaObjectProxy(source)
        when:
        proxy.callMethod("testMethod", null)
        then:
        1 * source.testMethod()
    }

    def "callMethod - alias method - call method"() {
        given:
        def source = Spy(new Object() {
            @LuaMethod(name="aliasMethod")
            void testMethod() {}
        })
        def proxy = new LuaObjectProxy(source)
        when:
        proxy.callMethod("aliasMethod", null)
        then:
        0 * source.aliasMethod()
        1 * source.testMethod()
    }

    def "callMethod - two methods with same alias - call method with matching parameters"() {
        given:
        def source = Spy(new Object() {
            @LuaMethod(name="aliasMethod")
            void testMethod() {}

            @LuaMethod(name="aliasMethod")
            void anotherMethod(String param) {}
        })
        def proxy = new LuaObjectProxy(source)
        when:
        proxy.callMethod("aliasMethod", ["test"] as Object[])
        then:
        0 * source.aliasMethod()
        0 * source.testMethod()
        1 * source.anotherMethod("test")
    }

    def "callMethod - two methods with same alias and same args - fails"() {
        given:
        def source = Spy(new Object() {
            @LuaMethod(name="aliasMethod")
            void testMethod(String param) {}

            @LuaMethod(name="aliasMethod")
            void anotherMethod(String param) {}
        })
        def proxy = new LuaObjectProxy(source)
        when:
        proxy.callMethod("aliasMethod", ["test"] as Object[])
        then:
        thrown(NoSuchMethodException)
    }

    def "callMethod - null as arguments - calls method with no arguments"() {
        given:
        def source = Spy(new Object() {
            @LuaMethod
            void testMethod() {}
        })
        def proxy = new LuaObjectProxy(source)
        when:
        proxy.callMethod("testMethod", null)
        then:
        1 * source.testMethod()
    }

    def "callMethod - empty array as arguments - calls method with no arguments"() {
        given:
        def source = Spy(new Object() {
            @LuaMethod
            void testMethod() {}
        })
        def proxy = new LuaObjectProxy(source)
        when:
        proxy.callMethod("testMethod", [] as Object[])
        then:
        1 * source.testMethod()
    }

    def "callMethod - arguments - passes arguments to method"() {
        given:
        def source = Spy(new Object() {
            @LuaMethod
            void testMethod(def arg1, def arg2, def arg3) {}
        })
        def proxy = new LuaObjectProxy(source)
        when:
        proxy.callMethod("testMethod", ["first", "second", 3] as Object[])
        then:
        1 * source.testMethod("first", "second", 3)
    }

    def "callMethod - too many arguments - fails"() {
        given:
        def source = new Object() {
            @LuaMethod
            void testMethod(def arg1) {}
        }
        def proxy = new LuaObjectProxy(source)
        when:
        proxy.callMethod("testMethod", ["first", "second"] as Object[])
        then:
        thrown(NoSuchMethodException)
    }

    def "callMethod - too few arguments - fails"() {
        given:
        def source = new Object() {
            @LuaMethod
            void testMethod(def arg1, def arg2) {}
        }
        def proxy = new LuaObjectProxy(source)
        when:
        proxy.callMethod("testMethod", ["first"] as Object[])
        then:
        thrown(NoSuchMethodException)
    }

    def "callMethod - lua specific argument - transforms argument before passing it to method"() {
        given:
        def source = Spy(new Object() {
            @LuaMethod
            void testMethod(def arg1, def arg2, def arg3) {}
        })
        def proxy = new LuaObjectProxy(source)
        when:
        proxy.callMethod("testMethod", [new LuaObjectProxy("Hello world"), "second", 3] as Object[])
        then:
        1 * source.testMethod("Hello world", "second", 3)
    }

    def "callMethod - method result is returned"() {
        given:
        def source = new Object() {
            @LuaMethod
            Object testMethod() {
                return "Hello world"
            }
        }
        def proxy = new LuaObjectProxy(source)
        when:
        def result = proxy.callMethod("testMethod", null)
        then:
        result == "Hello world"
    }

    def "callMethod - method returns unsafe lua object - transforms result before returning it"() {
        given:
        def source = new Object() {
            @LuaMethod
            Object testMethod() {
                return LuaObjectsSpec.UNSAFE_LUA_OBJECT
            }
        }
        def proxy = new LuaObjectProxy(source)
        when:
        def result = proxy.callMethod("testMethod", null)
        then:
        LuaObjects.isSafeLuaObject(result)
    }

    def "callMethod - multiple methods with name - fails"() {
        given:
        def source = new Object() {
            @LuaMethod
            void publicMethod() {}

            @LuaMethod
            void publicMethod(def input) {}
        }
        def proxy = new LuaObjectProxy(source)
        when:
        proxy.callMethod("testMethod", null)
        then:
        thrown(NoSuchMethodException)
    }

    def "callMethod - wrong argument types - fails"() {
        given:
        def source = new Object() {
            @LuaMethod
            void testMethod(Map input) {}
        }
        def proxy = new LuaObjectProxy(source)
        when:
        proxy.callMethod("testMethod", ["Hello world"] as Object[])
        then:
        thrown(NoSuchMethodException) //TODO was expecting IllegalArgumentException
    }

}
