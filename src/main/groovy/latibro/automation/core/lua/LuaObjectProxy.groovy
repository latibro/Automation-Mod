package latibro.automation.core.lua


import org.codehaus.groovy.reflection.CachedMethod

import javax.annotation.Nonnull
import javax.annotation.Nullable

class LuaObjectProxy {

    private final Object source

    LuaObjectProxy(@Nonnull Object source) {
        this.source = Objects.requireNonNull(source)
    }

    @Nonnull
    Object getSource() {
        return source
    }

    private List<CachedMethod> getMethodList() {
        return LuaMethods.getLuaMethodList(source.metaClass)
    }

    private CachedMethod findLuaMethod(String methodName, Object[] arguments) throws NoSuchMethodException {
        return LuaMethods.findLuaMethod(source.metaClass, methodName, arguments)
    }

    @Nonnull
    String[] getMethodNames() {
        return getMethodList().collect(LuaMethods::getLuaMethodName).unique()
    }

    private Object callCachedMethod(CachedMethod method, Object[] arguments) throws Exception {
        return method.doMethodInvoke(source, arguments)
    }

    @Nullable
    Object callMethod(@Nonnull String methodName, @Nullable Object[] arguments) throws Exception {
        Object[] nativeArguments = fromLuaArguments(arguments)

        def method = findLuaMethod(methodName, nativeArguments)

        Object nativeResult = callCachedMethod(method, nativeArguments)

        Object luaResult = toLuaResult(nativeResult)

        return luaResult
    }

    private static Object[] fromLuaArguments(Object[] arguments) {
        if (arguments == null) {
            return null
        }
        return arguments.collect(LuaObjects::fromLuaObject)
    }

    private static Object toLuaResult(Object result) {
        return LuaObjects.toLuaObject(result)
    }

}
