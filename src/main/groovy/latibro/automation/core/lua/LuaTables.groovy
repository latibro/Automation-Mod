package latibro.automation.core.lua

import javax.annotation.Nullable

final class LuaTables {

    private LuaTables() {
    }

    static boolean isSafeLuaTable(@Nullable Object object) {
        if (object == null) {
            return true
        }
        if (object instanceof Map) {
            return !object.any { key, value -> !LuaObjects.isSafeLuaObject(key) || !LuaObjects.isSafeLuaObject(value) }
        }
        return false
    }

    static boolean isLuaTableCandidate(@Nullable Object object) {
        if (object instanceof Map) {
            return true
        }
        if (object instanceof List) {
            return true
        }
        if (object instanceof Object[]) {
            return true
        }
        if (object instanceof Collection) {
            return true
        }
        return false
    }

    @Nullable
    static Object toLuaTable(@Nullable Object object) {
        if (isSafeLuaTable(object)) {
            return object
        } else if (object instanceof Map) {
            def map = object.collectEntries { key, value -> [LuaObjects.toLuaObject(key), LuaObjects.toLuaObject(value)] }
            return map.asUnmodifiable()
        } else if (object instanceof List) {
            def map = object.withIndex(1).collectEntries { k, v -> [v, k] }
            return toLuaTable(map.asUnmodifiable())
        } else if (object instanceof Object[]) {
            return toLuaTable(object as List)
        } else if (object instanceof Collection) {
            return toLuaTable(object as List)
        }
        throw new ClassCastException()
    }

    @Nullable
    static Object fromLuaTable(@Nullable Object object) {
        if (object == null) {
            return null
        } else if (object instanceof Map) {
            def map = object.collectEntries { key, value -> [LuaObjects.fromLuaObject(key), LuaObjects.fromLuaObject(value)] }
            return map
        }
        throw new ClassCastException()
    }

    static boolean isList(Map map) {
        if (map == null) {
            return false
        } else if (map.isEmpty()) {
            return true
        }
        return (1..map.size()).every { index -> map.containsKey(LuaObjects.toLuaObject(index)) }
    }

    static List toList(Map map) {
        if (!isList(map)) {
            throw new IllegalArgumentException("Not a list")
        }
        return map.sort { it.key }.collect { it.value }
    }

}
