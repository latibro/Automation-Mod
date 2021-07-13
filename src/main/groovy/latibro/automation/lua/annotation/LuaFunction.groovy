package latibro.automation.lua.annotation

import java.lang.annotation.*

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target([ElementType.METHOD])
@interface LuaFunction {
    String name() default ""
}
