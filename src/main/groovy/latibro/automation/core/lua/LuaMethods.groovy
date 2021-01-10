package latibro.automation.core.lua

import latibro.automation.api.core.lua.LuaMethod
import org.codehaus.groovy.reflection.CachedMethod

import java.lang.reflect.Method

final class LuaMethods {

    private LuaMethods() {
    }

    static List<LuaMethod> getLuaMethodAnnotations(CachedMethod method) {
        def luaMethods = []
        if (!method) {
            return luaMethods
        }
        if (method.getAnnotation(LuaMethod)) {
            luaMethods.add(method.getAnnotation(LuaMethod))
        }
        luaMethods.addAll(getSuperMethods(method.getCachedMethod()).findResults { it.getAnnotation(LuaMethod) })
        return luaMethods
    }

    private static List<Method> getSuperMethods(final Method method) {
        if (!method) {
            return []
        }
        def superMethods = getSuperMethods(method, method.getDeclaringClass())
        return superMethods
    }

    private static List<Method> getSuperMethods(final Method method, Class cls) {
        def superMethods = (List) []
        if (!cls) {
            return superMethods
        }
        if (method.getDeclaringClass() != cls) {
            try {
                superMethods.add(cls.getMethod(method.getName(), method.getParameterTypes()))
            } catch (NoSuchMethodException ignored) {
            }
        }
        if (cls.getSuperclass()) {
            superMethods.addAll(getSuperMethods(method, cls.getSuperclass()))
        }
        cls.getInterfaces().each {
            superMethods.addAll(getSuperMethods(method, it))
        }
        return superMethods
    }

    static String getLuaMethodName(CachedMethod method) {
        def aliases = getLuaMethodAnnotations(method).findResults { it.name() ? it.name() : null }.unique()
        if (aliases.size() > 1) {
            throw new Exception("To many aliases for this method")
        }
        return !aliases.isEmpty() ? aliases?.first() : method.getName()
    }

    static boolean isLuaMethod(CachedMethod method) {
        return getLuaMethodAnnotations(method)
    }

    static List<CachedMethod> getLuaMethodList(MetaClass metaClass) {
        return metaClass.getMethods().findResults {
            it instanceof CachedMethod ? (CachedMethod) it : null
        }.findAll { isLuaMethod(it) }
    }

    static CachedMethod findLuaMethod(MetaClass metaClass, String methodName, Object[] arguments) throws NoSuchMethodException {
        def methodsByName = getLuaMethodList(metaClass).findAll { getLuaMethodName(it) == methodName }
        if (!methodsByName) {
            throw new NoSuchMethodException("No method matches name")
        }
        def methodsByArgs = methodsByName.findAll { it.isValidMethod(arguments as Object[]) }
        def uniqueMethods = methodsByArgs.unique { it.name }
        if (!uniqueMethods) {
            throw new NoSuchMethodException("No method match")
        } else if (uniqueMethods.size() > 1) {
            throw new NoSuchMethodException("Multiple methods match")
        }
        return uniqueMethods.first()
    }

}
