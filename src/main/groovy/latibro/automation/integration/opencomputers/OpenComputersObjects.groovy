package latibro.automation.integration.opencomputers

import latibro.automation.core.lua.LuaObjectProxy

public class OpenComputersObjects {

    private OpenComputersObjects() {
    }

    public static boolean isSafeOpenComputersObject(Object object) {
        if (object == null) {
            // Null - No need to do anything
            return true;
        } else if (object instanceof Boolean) {
            // Boolean is safe
            return true;
        } else if (object instanceof String) {
            // String is safe
            return true;
        } else if (object instanceof Double) {
            // Only Double is safe
            return true;
        } else if (object instanceof OpenComputersObjectProxy) {
            // Proxy object
            return true;
        } else if (object instanceof Map) {
            //TODO check if array-like has "n" entry
            return !((Map) object).entrySet().stream().anyMatch(o -> {
                Object key = ((Map.Entry) o).getKey();
                Object value = ((Map.Entry) o).getValue();
                return !isSafeOpenComputersObject(key) || !isSafeOpenComputersObject(value);
            });
        } else {
            return false;
        }
    }

    public static Object toOpenComputersObject(Object object) {
        if (isSafeOpenComputersObject(object)) {
            return object;
        } else if (object instanceof LuaObjectProxy) {
            return new OpenComputersObjectProxy((LuaObjectProxy) object);
        } else if (object instanceof Map) {
            Map map = new HashMap();
            ((Map) object).entrySet().forEach(o -> {
                Object key = ((Map.Entry) o).getKey();
                Object value = ((Map.Entry) o).getValue();
                Object ccKey = toOpenComputersObject(key);
                Object ccValue = toOpenComputersObject(value);
                map.put(ccKey, ccValue);
            });
            //TODO check if array-like and add "n" entry
            return Collections.unmodifiableMap(map);
        } else {
            throw new ClassCastException();
        }
    }

    public static Object fromOpenComputersObject(Object object) {
        if (object == null) {
            return null;
        } else if (object instanceof Number) {
            return object;
        } else if (object instanceof String) {
            return object;
        } else if (object instanceof Boolean) {
            return object;
        } else if (object instanceof OpenComputersObjectProxy) {
            return ((OpenComputersObjectProxy) object).getSource();
        } else if (object instanceof Map) {
            Map map = new HashMap();
            ((Map) object).entrySet().forEach(o -> {
                Object luaKey = ((Map.Entry) o).getKey();
                Object luaValue = ((Map.Entry) o).getValue();
                Object key = fromOpenComputersObject(luaKey);
                Object value = fromOpenComputersObject(luaValue);
                map.put(key, value);
            });
            //TODO check if array-like and remove "n" entry
            return map;
        } else {
            throw new ClassCastException(object.toString());
        }
    }

}
