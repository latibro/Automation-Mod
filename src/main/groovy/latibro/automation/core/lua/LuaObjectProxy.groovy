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

    protected boolean isLuaMethod(CachedMethod method) {
        if (getLuaObjectAnnotation()) {
            if (getLuaObjectAnnotation().allMethods()) {
                return true
            }
        }
        return getLuaMethodAnnotation(method)
    }

    private LuaObject getLuaObjectAnnotation() {
        return source.getClass().getAnnotation(LuaObject.class)
    }

    private static LuaMethod getLuaMethodAnnotation(CachedMethod method) {
        return method?.getAnnotation(LuaMethod.class)
    }

    private List<CachedMethod> getMethodList() {
        return source.metaClass.getMethods().collect { it instanceof CachedMethod ? (CachedMethod) it : null }.findAll(this::isLuaMethod)
    }

    private CachedMethod findMethod(String methodName, Object[] arguments) throws NoSuchMethodException {
        def aliasedMethodName = getMethodList().find { getLuaMethodAnnotation(it)?.name() == methodName }?.getName()
        def correctMethodName = aliasedMethodName ?: methodName
        def method = (CachedMethod) source.metaClass.getMetaMethod(correctMethodName, arguments)
        if (isLuaMethod(method)) {
            return method
        }
        throw new NoSuchMethodException("No method match")
    }

    private static String getMethodName(CachedMethod method) {
        return getLuaMethodAnnotation(method)?.name() ?: method.getName()
    }

    @Nonnull
    String[] getMethodNames() {
        return getMethodList().collect(this::getMethodName).unique()
    }

    private Object callCachedMethod(CachedMethod method, Object[] arguments) throws Exception {
        return method.doMethodInvoke(source, arguments)
    }

    @Nullable
    Object callMethod(@Nonnull String methodName, @Nullable Object[] arguments) throws Exception {
        Object[] nativeArguments = fromLuaArguments(arguments)

        def method = findMethod(methodName, nativeArguments)

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
