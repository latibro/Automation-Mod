package latibro.automation.integration.computercraft

import dan200.computercraft.api.lua.ILuaContext
import dan200.computercraft.api.lua.LuaException
import dan200.computercraft.api.peripheral.IComputerAccess
import dan200.computercraft.api.peripheral.IPeripheral
import latibro.automation.core.lua.LuaObjectProxy

import javax.annotation.Nonnull
import javax.annotation.Nullable

trait CCPeripheralTrait implements IPeripheral {

    abstract LuaObjectProxy getPeripheralAPIProxy()

    abstract String getComponentName()

    private ComputerCraftObjectProxy getPeripheralCCProxy() {
        return new ComputerCraftObjectProxy(getPeripheralAPIProxy())
    }

    @Nonnull
    @Override
    String getType() {
        return getComponentName()
    }

    @Nonnull
    @Override
    String[] getMethodNames() {
        return getPeripheralCCProxy().getMethodNames()
    }

    @Nullable
    @Override
    Object[] callMethod(@Nonnull IComputerAccess computerAccess, @Nonnull ILuaContext context, int methodIndex, @Nonnull Object[] arguments) throws LuaException, InterruptedException {
        try {
            if (computerAccess.getClass().getPackage().getName().startsWith("li.cil.oc")) {
                //TODO for some reason methods, found on both CC and OC, it seems the CC version hasAPIFor priority over OC methods when called from OC. Maybe because of use of OC ManagedPeripheral
                throw new RuntimeException("CC called from OC")
            }

            Object[] result = getPeripheralCCProxy().callMethod(context, methodIndex, arguments)
            return result
        } catch (Throwable e) {
            e.printStackTrace()
            throw e
        }
    }

    @Override
    boolean equals(@Nullable IPeripheral iPeripheral) {
        return iPeripheral == this
    }

}