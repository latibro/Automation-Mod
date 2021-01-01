package latibro.automation.integration.opencomputers


import latibro.automation.core.lua.LuaObjectProxy
import li.cil.oc.api.machine.Arguments
import li.cil.oc.api.machine.Context
import li.cil.oc.api.network.ManagedPeripheral
import li.cil.oc.api.prefab.AbstractValue

import javax.annotation.Nonnull

class OpenComputersObjectProxy extends AbstractValue implements ManagedPeripheral {

    protected final LuaObjectProxy source

    OpenComputersObjectProxy(@Nonnull LuaObjectProxy source) {
        this.source = Objects.requireNonNull(source)
    }

    @Nonnull
    LuaObjectProxy getSource() {
        return source
    }

    @Nonnull
    @Override
    String[] methods() {
        return source.getMethodNames()
    }

    @Override
    Object[] invoke(String method, Context context, Arguments arguments) throws Exception {
        Object[] luaArguments = fromOpenComputersArguments(arguments)

        Object luaResult = source.callMethod(method, luaArguments)

        Object ocResult = toOpenComputersResult(luaResult)

        //TODO maybe return null or empty array if result is null?
        return ocResult ? new Object[]{ocResult} : ocResult
    }

    private static Object[] fromOpenComputersArguments(Arguments arguments) {
        if (arguments == null) {
            return null
        }
        return arguments.collect(OpenComputersObjects::fromOpenComputersObject)
    }

    private static Object toOpenComputersResult(Object result) {
        return OpenComputersObjects.toOpenComputersObject(result)
    }

}
