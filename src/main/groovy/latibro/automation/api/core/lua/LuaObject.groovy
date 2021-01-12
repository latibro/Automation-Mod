package latibro.automation.api.core.lua;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target([ElementType.TYPE])
public @interface LuaObject {
    boolean allMethods() default false
}
