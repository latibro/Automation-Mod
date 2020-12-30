package latibro.automation.core.peripheral;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import latibro.automation.core.api.API;
import latibro.automation.core.context.Context;
import latibro.automation.core.context.ContextProvider;
import latibro.automation.integration.computercraft.ComputerCraftObjectProxy;
import latibro.automation.core.lua.LuaObjectProxy;
import latibro.automation.integration.opencomputers.OpenComputersObjectProxy;
import li.cil.oc.api.Network;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.network.ManagedPeripheral;
import li.cil.oc.api.network.Visibility;
import li.cil.oc.api.prefab.TileEntityEnvironment;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

// https://github.com/PC-Logix/OpenFM/blob/master/src/main/java/pcl/OpenFM/TileEntity/TileEntityRadio.java
// https://github.com/Vexatos/Computronics/blob/master/src/main/java/pl/asie/computronics/tile/TileEntityPeripheralBase.java
public abstract class PeripheralTileEntity extends TileEntityEnvironment implements ManagedPeripheral, IPeripheral, Peripheral, ContextProvider {

    protected PeripheralTileEntity() {
        init();
    }

    private void init() {
        initCC();
        initOC();
    }

    protected abstract String getComponentName();

    protected abstract API getPeripheralAPI();

    private LuaObjectProxy getPeripheralAPIProxy() {
        return new LuaObjectProxy(getPeripheralAPI());
    }

    @Override
    public Context createContext() {
        return new PeripheralContext(this);
    }

    @Override
    public Context createContext(Map properties) {
        return new PeripheralContext(this, properties);
    }

    // **** OpenComputers

    private void initOC() {
        node = Network.newNode(this, Visibility.Network).withComponent(getComponentName(), Visibility.Network).create();
    }

    private OpenComputersObjectProxy getPeripheralOCProxy() {
        return new OpenComputersObjectProxy(getPeripheralAPIProxy());
    }

    @Override
    public String[] methods() {
        return getPeripheralOCProxy().methods();
    }

    @Override
    public Object[] invoke(String method, li.cil.oc.api.machine.Context context, Arguments arguments) throws Exception {
        Object[] result = getPeripheralOCProxy().invoke(method, context, arguments);
        return result;
    }

    // **** ComputerCraft

    private void initCC() {
    }

    private ComputerCraftObjectProxy getPeripheralCCProxy() {
        return new ComputerCraftObjectProxy(getPeripheralAPIProxy());
    }

    @Nonnull
    @Override
    public String getType() {
        return getComponentName();
    }

    @Nonnull
    @Override
    public String[] getMethodNames() {
        return getPeripheralCCProxy().getMethodNames();
    }

    @Nullable
    @Override
    public Object[] callMethod(@Nonnull IComputerAccess computerAccess, @Nonnull ILuaContext context, int methodIndex, @Nonnull Object[] arguments) throws LuaException, InterruptedException {
        try {
            if (computerAccess.getClass().getPackage().getName().startsWith("li.cil.oc")) {
                //TODO for some reason methods, found on both CC and OC, it seems the CC version has priority over OC methods when called from OC. Maybe because of use of OC ManagedPeripheral
                System.out.println("CC called from OC");
            }

            Object[] result = getPeripheralCCProxy().callMethod(context, methodIndex, arguments);
            return result;
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean equals(@Nullable IPeripheral iPeripheral) {
        return iPeripheral == this;
    }

}
