package latibro.automation.integration.lua;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.IntStream;

public final class LuaTables {

    private LuaTables() {
    }

    public static boolean isSafeLuaTable(@Nullable Object object) {
        if (object == null) {
            return true;
        } else if (object instanceof Map) {
            return !((Map) object).entrySet().stream().anyMatch(o -> {
                Object key = ((Map.Entry) o).getKey();
                Object value = ((Map.Entry) o).getValue();
                return !LuaObjects.isSafeLuaObject(key) || !LuaObjects.isSafeLuaObject(value);
            });
        } else {
            return false;
        }
    }

    public static boolean isLuaTableCandidate(@Nullable Object object) {
        if (object instanceof Map) {
            return true;
        } else if (object instanceof List) {
            return true;
        } else if (object instanceof Object[]) {
            return true;
        } else if (object instanceof Collection) {
            return true;
        } else {
            return false;
        }
    }

    @Nullable
    public static Object toLuaTable(@Nullable Object object) {
        if (isSafeLuaTable(object)) {
            return object;
        } else if (object instanceof Map) {
            Map map = new HashMap();
            ((Map) object).entrySet().forEach(o -> {
                Object key = ((Map.Entry) o).getKey();
                Object value = ((Map.Entry) o).getValue();
                Object luaKey = LuaObjects.toLuaObject(key);
                Object luaValue = LuaObjects.toLuaObject(value);
                map.put(luaKey, luaValue);
            });
            return Collections.unmodifiableMap(map);
        } else if (object instanceof List) {
            List list = (List) object;
            Map map = new HashMap();
            IntStream.range(0, list.size()).forEach(index -> {
                Object key = index+1; // Lua work with 1 as first on array/list
                Object value = list.get(index);
                map.put(key, value);
            });
            return toLuaTable(Collections.unmodifiableMap(map));
        } else if (object instanceof Object[]) {
            return toLuaTable(Arrays.asList((Object[]) object));
        } else if (object instanceof Collection) {
            return toLuaTable(new ArrayList((Collection) object));
        } else {
            throw new ClassCastException();
        }
    }

    @Nullable
    public static Object fromLuaTable(@Nullable Object object) {
        if (object == null) {
            return null;
        } else if (object instanceof Map) {
            Map map = new HashMap();
            ((Map) object).entrySet().forEach(o -> {
                Object luaKey = ((Map.Entry) o).getKey();
                Object luaValue = ((Map.Entry) o).getValue();
                Object key = LuaObjects.fromLuaObject(luaKey);
                Object value = LuaObjects.fromLuaObject(luaValue);
                map.put(key, value);
            });
            return map;
        } else {
            throw new ClassCastException();
        }
    }

    public static boolean isList(Map map) {
        if (map == null) {
            return false;
        }
        for (int index=0; index < map.size(); index++) {
            if (!map.containsKey(LuaObjects.toLuaObject(index+1))) {
                return false;
            }
        }
        return true;
    }

    public static List toList(Map map) {
        if (!isList(map)) {
            throw new IllegalArgumentException("Not a list");
        }
        List list = new ArrayList();
        for (int index=0; index < map.size(); index++) {
            list.add(index, map.get(LuaObjects.toLuaObject(index+1)));
        }
        return list;
    }

}
