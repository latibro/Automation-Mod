package latibro.automation.integration.computercraft;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.ILuaObject;
import dan200.computercraft.api.lua.LuaException;
import latibro.automation.AutomationMod;
import latibro.automation.integration.lua.LuaObjectProxy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Objects;

// https://github.com/SquidDev-CC/CC-Tweaked/blob/master/src/main/java/dan200/computercraft/api/lua/ILuaObject.java
public class ComputerCraftObjectProxy implements ILuaObject {

    protected final LuaObjectProxy source;

    public ComputerCraftObjectProxy(@Nonnull LuaObjectProxy source) {
        this.source = Objects.requireNonNull(source);
    }

    @Nonnull
    public LuaObjectProxy getSource() {
        return source;
    }

    @Nonnull
    @Override
    public String[] getMethodNames() {
        return source.getMethodNames();
    }

    private String getMethodName(int methodIndex) throws NoSuchMethodException {
        if (methodIndex >= getMethodNames().length) {
            throw new NoSuchMethodException();
        } else {
            return getMethodNames()[methodIndex];
        }
    }

    @Nullable
    @Override
    public Object[] callMethod(@Nonnull ILuaContext iLuaContext, int methodIndex, @Nonnull Object[] arguments) throws LuaException, InterruptedException {
        try {
            String methodName = getMethodName(methodIndex);

            Object[] luaArguments = fromComputerCraftArguments(arguments);

            Object luaResult = source.callMethod(methodName, luaArguments);

            Object ccResult = toComputerCraftResult(luaResult);

            //TODO maybe return null or empty array if result is null?
            return new Object[] {ccResult};
        } catch (Exception e) {
            AutomationMod.logger.error("CCProxy.callMethod - exception", e);
            e.printStackTrace();
            throw new LuaException(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    private Object[] fromComputerCraftArguments(Object[] arguments) {
        if (arguments == null) {
            return null;
        } else {
            return Arrays.stream(arguments).map(ComputerCraftObjects::fromComputerCraftObject).toArray();
        }
    }

    private Object toComputerCraftResult(Object result) {
        return ComputerCraftObjects.toComputerCraftObject(result);
    }

}
