package latibro.automation.core.lua;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target([ElementType.TYPE, ElementType.TYPE_USE])
public @interface LuaObject {
    boolean allMethods() default false;
}
