package latibro.automation.integration.computercraft;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import latibro.automation.AutomationMod;
import latibro.automation.source.SourceBoxComputerIntegration;
import latibro.automation.source.SourceBoxTileEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

// https://github.com/SquidDev-CC/CC-Tweaked/blob/master/src/main/java/dan200/computercraft/api/peripheral/IPeripheral.java
public class SourceBoxPeripheral implements IPeripheral {

    private final SourceBoxTileEntity tileEntity;
    private final SourceBoxComputerIntegration computerIntegration;
    private final ComputerCraftWrappedObject proxy;

    public SourceBoxPeripheral(SourceBoxTileEntity tileEntity) {
        this.tileEntity = tileEntity;
        computerIntegration = new SourceBoxComputerIntegration();
        proxy = new ComputerCraftWrappedObject(computerIntegration);
    }

    @Nonnull
    @Override
    public String getType() {
        return "source_box";
    }

    @Nonnull
    @Override
    public String[] getMethodNames() {
        return proxy.getMethodNames();
    }

    @Nullable
    @Override
    public Object[] callMethod(@Nonnull IComputerAccess computerAccess, @Nonnull ILuaContext context, int methodIndex, @Nonnull Object[] arguments) throws LuaException, InterruptedException {

        Object x = new Object();

        return new Object[]{x};

//        Object[] result = proxy.callMethod(context, methodIndex, arguments);
//        return result;

//        Object wrappedResult;
//        if (computerAccess.getClass().getPackage().getName().startsWith("li.cil.oc")) {
//            //TODO for some reason methods, found on both CC and OC, it seems the CC version has priority over OC methods when called from OC
//            AutomationMod.logger.debug("CC:callMethod - OC wrapping");
//            wrappedResult = new OpenComputersObjectConverter().wrapObject(result);
//        } else {
//            AutomationMod.logger.debug("CC:callMethod - CC wrapping");
//            wrappedResult = new ComputerCraftObjectConverter().wrapObject(result);
//        }
//
//        return new Object[]{wrappedResult};
    }

    @Override
    public boolean equals(@Nullable IPeripheral iPeripheral) {
        return iPeripheral == this;
    }

}
