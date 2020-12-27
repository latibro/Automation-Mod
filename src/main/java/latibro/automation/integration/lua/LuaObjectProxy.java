package latibro.automation.integration.lua;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

public class LuaObjectProxy {

    private final Object source;

    public LuaObjectProxy(@Nonnull Object source) {
        this.source = Objects.requireNonNull(source);
    }

    @Nonnull
    public Object getSource() {
        return source;
    }

    private Method[] getMethodList() {
        return source.getClass().getMethods();
    }

    private Method findMethod(String methodName, Object[] arguments) throws NoSuchMethodException {
        Method[] matchingMethods = Arrays.stream(getMethodList()).filter(m -> m.getName().equals(methodName)).toArray(Method[]::new);
        if (matchingMethods.length == 0) {
            throw new NoSuchMethodException("No method match");
        } else if (matchingMethods.length > 1) {
            throw new NoSuchMethodException("Multiple methods match");
        }
        return matchingMethods[0];
    }

    @Nonnull
    public String[] getMethodNames() {
        return Arrays.stream(getMethodList()).map(Method::getName).distinct().toArray(String[]::new);
    }

    private Object callMethod(Method method, Object[] arguments) throws Exception {
        return method.invoke(source, arguments);
    }

    @Nullable
    public Object callMethod(@Nonnull String methodName, @Nullable Object[] arguments) throws Exception {
        Object[] nativeArguments = fromLuaArguments(arguments);

        Method method = findMethod(methodName, nativeArguments);

        Object nativeResult = callMethod(method, nativeArguments);

        Object luaResult = toLuaResult(nativeResult);

        return luaResult;
    }

    private Object[] fromLuaArguments(Object[] arguments) {
        if (arguments == null) {
            return null;
        } else {
            return Arrays.stream(arguments).map(LuaObjects::fromLuaObject).toArray();
        }
    }

    private Object toLuaResult(Object result) {
        return LuaObjects.toLuaObject(result);
    }

}
