package latibro.automation.core.lua;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target([ElementType.METHOD])
public @interface LuaMethod {
    String name() default "";
}
