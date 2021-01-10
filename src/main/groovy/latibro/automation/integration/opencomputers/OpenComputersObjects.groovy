package latibro.automation.integration.opencomputers


import latibro.automation.core.lua.LuaObjectProxy

class OpenComputersObjects {

    private OpenComputersObjects() {
    }

    static boolean isSafeOpenComputersObject(Object object) {
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
        } else if (object instanceof OpenComputersObjectProxy) {
            // Proxy object
            return true
        } else if (object instanceof Map) {
            //TODO check if array-like has "n" entry
            return !object.any { key, value -> !isSafeOpenComputersObject(key) || !isSafeOpenComputersObject(value) }
        }
        return false
    }

    static Object toOpenComputersObject(Object object) {
        if (isSafeOpenComputersObject(object)) {
            return object
        } else if (object instanceof LuaObjectProxy) {
            return new OpenComputersObjectProxy((LuaObjectProxy) object)
        } else if (object instanceof Map) {
            def map = object.collectEntries { key, value -> [toOpenComputersObject(key), toOpenComputersObject(value)] }
            //TODO check if array-like and add "n" entry
            return map.asUnmodifiable()
        }
        throw new ClassCastException()
    }

    static Object fromOpenComputersObject(Object object) {
        if (object == null) {
            return null
        } else if (object instanceof Number) {
            return object
        } else if (object instanceof String) {
            return object
        } else if (object instanceof Boolean) {
            return object
        } else if (object instanceof byte[]) {
            return new String(object)
        } else if (object instanceof OpenComputersObjectProxy) {
            return ((OpenComputersObjectProxy) object).getSource()
        } else if (isListLikeOCTable(object)) {
            // See comments on toListLikeMap() why this check is here
            return fromOpenComputersObject(fromListLikeOCTable(object as Map))
        } else if (object instanceof Map) {
            return object.collectEntries { key, value -> [fromOpenComputersObject(key), fromOpenComputersObject(value)] }
        }
        throw new ClassCastException(object as String)
    }

    /*
     * See comments on toListLikeMap() why this methods is here
     */

    private static boolean isListLikeOCTable(Object object) {
        if (object == null) {
            return false
        }
        if (object !instanceof Map) {
            return false
        }
        if (!isInstanceOfOCBuildInObject(object)) {
            return false
        }
        def map = (Map) object
        if (map.isEmpty()) {
            return false
        }
        if (map.get("n") == map.size() - 1) {
            return (1..map.size() - 1).every { index -> map.containsKey(index as Double) }
        }
        return (1..map.size()).every { index -> map.containsKey(index as Double) }
    }

    /*
     * See comments on toListLikeMap() why this methods is here
     */

    protected static boolean isInstanceOfOCBuildInObject(Object object) {
        if (object.getClass().getName().startsWith("li.cil.repack.com.naef.jnlua.")) {
            return true
        }
        return false;
    }

    /*
     * OC fails if you try to iterate an array-like {"first", "second"} map
     * See reported issues https://github.com/MightyPirates/OpenComputers/issues/2319
     * So if it looks like an array-like map, we do a special hacked parsing
     */

    protected static Map fromListLikeOCTable(Object object) {
        Map map = new HashMap(object as Map)
        map.remove("n")
        return (1..map.size()).collectEntries { index ->
            [index as Double, ((Map) map).get(index as Double)]
        }
    }

}
