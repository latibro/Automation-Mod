package latibro.automation.core.lua

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.api.core.lua.LuaObject
import org.codehaus.groovy.reflection.CachedMethod

import javax.annotation.Nonnull
import javax.annotation.Nullable
import java.lang.reflect.Method

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
        if (hasLuaObjectAllMethods(source.getClass())) {
            return true
        }
        return getLuaMethodAnnotations(method)
    }

    private static boolean hasLuaObjectAllMethods(Class cls) {
        return getLuaObjectAnnotations(cls).any { it.allMethods() }
    }

    protected static boolean isLuaObject(Class cls) {
        return getLuaObjectAnnotations(cls)
    }

    private static List<LuaObject> getLuaObjectAnnotations(Class cls) {
        def luaObjects = (List) []
        if (!cls) {
            return luaObjects
        }
        luaObjects.addAll(cls.getAnnotationsByType(LuaObject))
        if (cls.getSuperclass()) {
            luaObjects.addAll(getLuaObjectAnnotations(cls.getSuperclass()))
        }
        cls.getInterfaces().each {
            luaObjects.addAll(getLuaObjectAnnotations(it))
        }
        return luaObjects
    }

    private static List<LuaMethod> getLuaMethodAnnotations(CachedMethod method) {
        def luaMethods = []
        if (!method) {
            return luaMethods
        }
        if (method.getAnnotation(LuaMethod)) {
            luaMethods.add(method.getAnnotation(LuaMethod))
        }
        luaMethods.addAll(getSuperMethods(method.getCachedMethod()).findResults {it.getAnnotation(LuaMethod)})
        return luaMethods
    }

    private static List<Method> getSuperMethods(final Method myMethod) {
        if (!myMethod) {
            return []
        }
        def superMethods = getSuperMethods(myMethod, myMethod.getDeclaringClass())
        return superMethods
    }

    private static List<Method> getSuperMethods(final Method myMethod, Class cls) {
        def superMethods = (List) []
        if (!cls) {
            return superMethods
        }
        if (myMethod.getDeclaringClass() != cls) {
            try {
                superMethods.add(cls.getMethod(myMethod.getName(), myMethod.getParameterTypes()))
            } catch (NoSuchMethodException ignored) {}
        }
        if (cls.getSuperclass()) {
            superMethods.addAll(getSuperMethods(myMethod, cls.getSuperclass()))
        }
        cls.getInterfaces().each {
            superMethods.addAll(getSuperMethods(myMethod, it))
        }
        return superMethods
    }

    private List<CachedMethod> getMethodList() {
        return getMethodList(source.metaClass)
        //TODO look for LuaMethod in interfaces (Class.getInterfaces) and supers (Class.getSuperclass)
        //return source.metaClass.getMethods().collect { it instanceof CachedMethod ? (CachedMethod) it : null }.findAll(this::isLuaMethod)
    }

    private List<CachedMethod> getMethodList(MetaClass metaClass) {
        return metaClass.getMethods().findResults {
            it instanceof CachedMethod ? (CachedMethod) it : null
        }.findAll(this::isLuaMethod)
    }

    private CachedMethod findMethod(String methodName, Object[] arguments) throws NoSuchMethodException {
        def methodsByName = getMethodList().findAll { getMethodName(it) == methodName }
        if (!methodsByName) {
            throw new NoSuchMethodException("No method matches name")
        }
        def methodsByArgs = methodsByName.findAll { it.isValidMethod(arguments as Object[]) }
        if (!methodsByArgs) {
            throw new NoSuchMethodException("No method match")
        } else if (methodsByArgs.size() > 1) {
            throw new NoSuchMethodException("Multiple methods match")
        }
        return methodsByArgs.first()
    }

    private static String getMethodName(CachedMethod method) {
        def aliases = getLuaMethodAnnotations(method).findResults { it.name() ? it.name() : null }.unique()
        if (aliases.size() > 1) {
            throw new Exception("To many aliases for this method")
        }
        return !aliases.isEmpty() ? aliases?.first() : method.getName()
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
