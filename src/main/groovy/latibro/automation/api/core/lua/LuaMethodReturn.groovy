package latibro.automation.api.core.lua

import java.lang.annotation.*

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target([ElementType.METHOD])
@interface LuaMethodReturn {
    //TODO Object transformer() default ""
}
