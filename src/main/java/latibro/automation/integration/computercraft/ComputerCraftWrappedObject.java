package latibro.automation.integration.computercraft;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.ILuaObject;
import dan200.computercraft.api.lua.LuaException;
import latibro.automation.AutomationMod;
import latibro.automation.integration.CommonLuaComputerObjectWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

// https://github.com/SquidDev-CC/CC-Tweaked/blob/master/src/main/java/dan200/computercraft/api/lua/ILuaObject.java
public class ComputerCraftWrappedObject implements ILuaObject {

    protected final CommonLuaComputerObjectWrapper wrappedObject;

    public ComputerCraftWrappedObject(CommonLuaComputerObjectWrapper object) {
        AutomationMod.logger.debug("CCWrapper:constructor - {}", object);
        this.wrappedObject = object;
    }

    public ComputerCraftWrappedObject(Object object) {
        this(new CommonLuaComputerObjectWrapper(object));
    }

    @Nonnull
    @Override
    public String[] getMethodNames() {
        return wrappedObject.getMethodNames();
    }

    @Nullable
    @Override
    public Object[] callMethod(@Nonnull ILuaContext iLuaContext, int methodIndex, @Nonnull Object[] arguments) throws LuaException, InterruptedException {
        try {
            String methodName = getMethodNames()[methodIndex];

            Object[] wrappedArguments = (Object[]) new ComputerCraftObjectConverter().unwrapObject(arguments);

            Object wrappedResult = wrappedObject.callMethod(methodName, wrappedArguments);

            Object result = new ComputerCraftObjectConverter().wrapObject(wrappedResult);

            //TODO maybe return null or empty array if result is null?
            return new Object[] {result};
        } catch (Exception e) {
            AutomationMod.logger.error("CCWrapper.callMethod - exception", e);
            e.printStackTrace();
            throw new LuaException(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public Object getWrappedObject() {
        return wrappedObject;
    }

}
