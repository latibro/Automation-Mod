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
            return !((Map) object).entrySet().stream().anyMatch(o -> {
                Object key = ((Map.Entry) o).getKey()
                Object value = ((Map.Entry) o).getValue()
                return !isSafeComputerCraftObject(key) || !isSafeComputerCraftObject(value)
            })
        } else {
            return false
        }
    }

    static Object toComputerCraftObject(Object object) {
        if (isSafeComputerCraftObject(object)) {
            return object
        } else if (object instanceof LuaObjectProxy) {
            return new ComputerCraftObjectProxy((LuaObjectProxy) object)
        } else if (object instanceof Map) {
            Map map = new HashMap()
            ((Map) object).entrySet().forEach(o -> {
                Object key = ((Map.Entry) o).getKey()
                Object value = ((Map.Entry) o).getValue()
                Object ccKey = toComputerCraftObject(key)
                Object ccValue = toComputerCraftObject(value)
                map.put(ccKey, ccValue)
            })
            return Collections.unmodifiableMap(map)
        } else {
            throw new ClassCastException()
        }
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
            Map map = new HashMap()
            ((Map) object).entrySet().forEach(o -> {
                Object luaKey = ((Map.Entry) o).getKey()
                Object luaValue = ((Map.Entry) o).getValue()
                Object key = fromComputerCraftObject(luaKey)
                Object value = fromComputerCraftObject(luaValue)
                map.put(key, value)
            })
            return map
        } else {
            throw new ClassCastException(object.toString())
        }
    }

}
