package latibro.automation.integration.opencomputers

import latibro.automation.core.lua.LuaObjectProxy
import li.cil.oc.api.Network
import li.cil.oc.api.machine.Arguments
import li.cil.oc.api.machine.Context
import li.cil.oc.api.network.*
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity

trait OCTileEntityEnvironmentTrait implements ManagedPeripheral, Environment {

    private static final String TAG_NODE = "oc:node";
    private Node node;

    void init() {
        node = Network.newNode(this, Visibility.Network).withComponent(getComponentName(), Visibility.Network).create()
    }

    abstract TileEntity getSelf()

    abstract LuaObjectProxy getPeripheralAPIProxy()

    abstract String getComponentName()

    private OpenComputersObjectProxy getPeripheralOCProxy() {
        return new OpenComputersObjectProxy(getPeripheralAPIProxy())
    }

    @Override
    String[] methods() {
        return getPeripheralOCProxy().methods()
    }

    @Override
    Object[] invoke(String method, Context context, Arguments arguments) throws Exception {
        Object[] result = getPeripheralOCProxy().invoke(method, context, arguments)
        return result
    }

    Node node() {
        return this.node;
    }

    void onConnect(Node node) {
    }

    void onDisconnect(Node node) {
    }

    void onMessage(Message message) {
    }

    void onLoad() {
        Network.joinOrCreateNetwork(getSelf());
    }

    void onChunkUnload() {
        if (this.node != null) {
            this.node.remove();
        }

    }

    void invalidate() {
        if (this.node != null) {
            this.node.remove();
        }
    }

    void readFromNBT(NBTTagCompound nbt) {
        if (this.node != null && this.node.host() == this) {
            this.node.load(nbt.getCompoundTag(TAG_NODE));
        }
    }

    NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        if (this.node != null && this.node.host() == this) {
            NBTTagCompound nodeNbt = new NBTTagCompound();
            this.node.save(nodeNbt);
            nbt.setTag(TAG_NODE, nodeNbt);
        }
        return nbt;
    }

}