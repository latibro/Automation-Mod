package latibro.automation.api.core.lua

import java.lang.annotation.*

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target([ElementType.PARAMETER])
@interface LuaMethodParameter {
    //TODO Object transformer() default ""
}
