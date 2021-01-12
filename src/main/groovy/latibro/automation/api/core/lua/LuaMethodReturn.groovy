package latibro.automation.api.core.lua;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target([ElementType.METHOD])
public @interface LuaMethodReturn {
    //TODO Object transformer() default ""
}
