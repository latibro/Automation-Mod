package latibro.automation.integration.computercraft

import latibro.automation.core.lua.LuaObjectProxy

final class ComputerCraftObjects {

    private ComputerCraftObjects() {
    }

    static boolean isSafeComputerCraftObject(Object object) {
        if (object == null) {
            // Null - No need to do anything
            return true
        } else if (object instanceof Boolean) {
            // Boolean is safe
            return true
        } else if (object instanceof String) {
            // String is safe
            return true
        } else if (object instanceof Double) {
            // Only Double is safe
            return true
        } else if (object instanceof ComputerCraftObjectProxy) {
            // Proxy object
            return true
        } else if (object instanceof Map) {
            return !object.any { key, value -> !isSafeComputerCraftObject(key) || !isSafeComputerCraftObject(value) }
        }
        return false
    }

    static Object toComputerCraftObject(Object object) {
        if (isSafeComputerCraftObject(object)) {
            return object
        } else if (object instanceof LuaObjectProxy) {
            return new ComputerCraftObjectProxy((LuaObjectProxy) object)
        } else if (object instanceof Map) {
            def map = object.collectEntries { key, value -> [toComputerCraftObject(key), toComputerCraftObject(value)] }
            return map.asUnmodifiable()
        }
        throw new ClassCastException()
    }

    static Object fromComputerCraftObject(Object object) {
        if (object == null) {
            return null
        } else if (object instanceof Number) {
            return object
        } else if (object instanceof String) {
            return object
        } else if (object instanceof Boolean) {
            return object
        } else if (object instanceof ComputerCraftObjectProxy) {
            return ((ComputerCraftObjectProxy) object).getSource()
        } else if (object instanceof Map) {
            def map = object.collectEntries { key, value -> [fromComputerCraftObject(key), fromComputerCraftObject(value)] }
            return map
        }
        throw new ClassCastException(object.toString())
    }

}
