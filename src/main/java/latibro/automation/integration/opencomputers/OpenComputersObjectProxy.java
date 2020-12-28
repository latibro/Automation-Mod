package latibro.automation.integration.opencomputers;

import latibro.automation.AutomationMod;
import latibro.automation.integration.lua.LuaObjectProxy;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.ManagedPeripheral;
import li.cil.oc.api.prefab.AbstractValue;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Objects;

public class OpenComputersObjectProxy extends AbstractValue implements ManagedPeripheral {

    protected final LuaObjectProxy source;

    public OpenComputersObjectProxy(@Nonnull LuaObjectProxy source) {
        this.source = Objects.requireNonNull(source);
    }

    @Nonnull
    public LuaObjectProxy getSource() {
        return source;
    }

    @Nonnull
    @Override
    public String[] methods() {
        return source.getMethodNames();
    }

    @Override
    public Object[] invoke(String method, Context context, Arguments arguments) throws Exception {
        try {
            try {
                //TODO OC fails if input is is array-like {"first", "second"} https://github.com/MightyPirates/OpenComputers/issues/2319
                for (Object o : arguments) {
                    String.valueOf(o);
                }
            } catch (Throwable e) {
                throw new IllegalArgumentException("OC is unable to parse array-like tables");
            }

            Object[] luaArguments = fromOpenComputersArguments(arguments);

            Object luaResult = source.callMethod(method, luaArguments);

            Object ocResult = toOpenComputersResult(luaResult);

            //TODO maybe return null or empty array if result is null?
            return new Object[] {ocResult};
        } catch (Exception e) {
            AutomationMod.logger.error("OCProxy.invoke - exception", e);
            e.printStackTrace();
            throw e;
        }
    }

    private Object[] fromOpenComputersArguments(Arguments arguments) {
        if (arguments == null) {
            return null;
        } else {
            return Arrays.stream(arguments.toArray()).map(OpenComputersObjects::fromOpenComputersObject).toArray();
        }
    }

    private Object toOpenComputersResult(Object result) {
        return OpenComputersObjects.toOpenComputersObject(result);
    }

}
