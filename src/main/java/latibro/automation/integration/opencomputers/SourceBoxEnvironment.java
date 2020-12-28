package latibro.automation.integration.opencomputers;

import latibro.automation.integration.lua.LuaObjectProxy;
import latibro.automation.source.SourceBoxComputerIntegration;
import latibro.automation.source.SourceBoxTileEntity;
import li.cil.oc.api.Network;
import li.cil.oc.api.driver.NamedBlock;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.ManagedPeripheral;
import li.cil.oc.api.network.Visibility;
import li.cil.oc.api.prefab.AbstractManagedEnvironment;

public class SourceBoxEnvironment extends AbstractManagedEnvironment implements NamedBlock, ManagedPeripheral {

    private final SourceBoxTileEntity tileEntity;
    private final OpenComputersObjectProxy proxy;

    public SourceBoxEnvironment(SourceBoxTileEntity tileEntity) {
        setNode(Network.newNode(this, Visibility.Network).withComponent(preferredName(), Visibility.Network).create());
        this.tileEntity = tileEntity;
        proxy = new OpenComputersObjectProxy(new LuaObjectProxy(new SourceBoxComputerIntegration()));
    }

    @Override
    public String preferredName() {
        return "source_box";
    }

    @Override
    public int priority() {
        return 10;
    }

    @Override
    public String[] methods() {
        return proxy.methods();
    }

    @Override
    public Object[] invoke(String method, Context context, Arguments arguments) throws Exception {
        Object[] result = proxy.invoke(method, context, arguments);
        return result;
    }

}
