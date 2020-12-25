package latibro.automation.integration;

import latibro.automation.AutomationMod;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class CommonLuaComputerObjectConverter {

    @Nullable
    public Object wrapObject(@Nullable Object object) {
        AutomationMod.logger.debug("ComputerObjectConverter.toComputerObject - {}", object);
        //TODO handle array/collection (maybe transform to Map as Lua doesn't have array)
        //TODO handle map (lua table)
        //TODO convert values of map/array/collection values
        if (object == null) {
            // Null - No need to do anything
            AutomationMod.logger.debug("ComputerObjectConverter.toComputerObject - null");
            return null;
        } else if (object instanceof CommonLuaComputerObjectWrapper) {
            // Already wrapped
            AutomationMod.logger.debug("ComputerObjectConverter.toComputerObject - wrapped");
            return object;
        } else if (object instanceof Boolean ||
                object instanceof String ||
                object instanceof Number
        ) {
            // Primitive - no need to wrap
            AutomationMod.logger.debug("ComputerObjectConverter.toComputerObject - primitive");
            return object;
        } else if (object instanceof Object[]) {
            // Array - convert each element
            AutomationMod.logger.debug("ComputerObjectConverter.toComputerObject - array");
            return Arrays.stream((Object[]) object).map(this::wrapObject).toArray();
        } else if (object instanceof Collection) {
            // Collection - convert elements, and return as array
            AutomationMod.logger.debug("ComputerObjectConverter.toComputerObject - collection");
            return ((Collection) object).stream().map(this::wrapObject).toArray();
        } else if (object instanceof Map) {
            // Map - convert both keys and elements
            AutomationMod.logger.debug("ComputerObjectConverter.toComputerObject - map");
            Map newMap = new HashMap();
            ((Map) object).forEach((k, v) -> newMap.put(wrapObject(k), wrapObject(v)));
            return newMap;
        } else {
            // Everyting else - Wrapping it
            AutomationMod.logger.debug("ComputerObjectConverter.toComputerObject - wrapping");
            AutomationMod.logger.debug("ComputerObjectConverter.toComputerObject - wrapping - type " + object.getClass().getName());
            return new CommonLuaComputerObjectWrapper(object);
        }
    }

    @Nullable
    public Object unwrapObject(@Nullable Object object) {
        AutomationMod.logger.debug("ComputerObjectConverter.fromComputerObject - {}", object);
        //TODO handled array/list/set
        //TODO handle map (maybe change some to array, as lua doesn't have array)
        //TODO convert values of map/array/list/set values
        if (object == null) {
            // Null - No need to do anything
            AutomationMod.logger.debug("ComputerObjectConverter.fromComputerObject - null");
            return null;
        } else if (object instanceof CommonLuaComputerObjectWrapper) {
            // Wrapped - Unwrapping
            AutomationMod.logger.debug("ComputerObjectConverter.fromComputerObject - unwrapping");
            //TODO maybe recursive just ot make use its wasn't double wrapped
            return ((CommonLuaComputerObjectWrapper) object).getWrappedObject();
        } else if (object instanceof Object[]) {
            // Array - Unwrapping elements
            AutomationMod.logger.debug("ComputerObjectConverter.fromComputerObject - array");
            AutomationMod.logger.debug("ComputerObjectConverter.fromComputerObject - array - type: {}", object.getClass().getName());
            AutomationMod.logger.debug("ComputerObjectConverter.fromComputerObject - array - length: {}", ((Object[]) object).length);
            return Arrays.stream((Object[]) object).map(this::unwrapObject).toArray();
        } else {
            // Everyting else - Passing it through
            AutomationMod.logger.debug("ComputerObjectConverter.fromComputerObject - Pass-through");
            AutomationMod.logger.debug("ComputerObjectConverter.fromComputerObject - Pass-through - type: {}", object.getClass().getName());
            return object;
        }
    }

}
