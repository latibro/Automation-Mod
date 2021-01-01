package latibro.automation.integration.computercraft

import dan200.computercraft.api.lua.ILuaContext
import dan200.computercraft.api.lua.ILuaObject
import dan200.computercraft.api.lua.LuaException
import latibro.automation.core.lua.LuaObjectProxy

import javax.annotation.Nonnull
import javax.annotation.Nullable

// https://github.com/SquidDev-CC/CC-Tweaked/blob/master/src/main/java/dan200/computercraft/api/lua/ILuaObject.java
class ComputerCraftObjectProxy implements ILuaObject {

    protected final LuaObjectProxy source

    ComputerCraftObjectProxy(@Nonnull LuaObjectProxy source) {
        this.source = Objects.requireNonNull(source)
    }

    @Nonnull
    LuaObjectProxy getSource() {
        return source
    }

    @Nonnull
    @Override
    String[] getMethodNames() {
        return source.getMethodNames()
    }

    private String getMethodName(int methodIndex) throws NoSuchMethodException {
        if (methodIndex >= getMethodNames().length) {
            throw new NoSuchMethodException()
        }
        return getMethodNames()[methodIndex]
    }

    @Nullable
    @Override
    Object[] callMethod(@Nonnull ILuaContext iLuaContext, int methodIndex, @Nonnull Object[] arguments) throws LuaException, InterruptedException {
        try {
            String methodName = getMethodName(methodIndex)

            Object[] luaArguments = fromComputerCraftArguments(arguments)

            Object luaResult = source.callMethod(methodName, luaArguments)

            Object ccResult = toComputerCraftResult(luaResult)

            //TODO maybe return null or empty array if result is null?
            return new Object[]{ccResult}
        } catch (Exception e) {
            throw new LuaException(e.getClass().getName() + ": " + e.getMessage())
        }
    }

    private static Object[] fromComputerCraftArguments(Object[] arguments) {
        if (arguments == null) {
            return null
        }
        return arguments.collect(ComputerCraftObjects::fromComputerCraftObject)
    }

    private static Object toComputerCraftResult(Object result) {
        return ComputerCraftObjects.toComputerCraftObject(result)
    }

}
