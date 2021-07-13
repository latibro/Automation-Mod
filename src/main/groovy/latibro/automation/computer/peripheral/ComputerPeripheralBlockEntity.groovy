package latibro.automation.computer.peripheral

import dan200.computercraft.api.lua.ILuaContext
import dan200.computercraft.api.lua.LuaException
import dan200.computercraft.api.peripheral.IComputerAccess
import dan200.computercraft.api.peripheral.IPeripheral
import groovy.transform.CompileStatic
import latibro.automation.api.API
import latibro.automation.core.lua.LuaObjectProxy
import latibro.automation.integration.computercraft.ComputerCraftObjectProxy
import latibro.automation.integration.opencomputers.OpenComputersObjectProxy
import li.cil.oc.api.Network
import li.cil.oc.api.machine.Arguments
import li.cil.oc.api.machine.Context
import li.cil.oc.api.network.*
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.Optional

import javax.annotation.Nonnull
import javax.annotation.Nullable

@CompileStatic
@Optional.InterfaceList([
        @Optional.Interface(iface = "dan200.computercraft.api.peripheral.IPeripheral", modid = "computercraft"),
        @Optional.Interface(iface = "li.cil.oc.api.network.ManagedPeripheral", modid = "opencomputers"),
        @Optional.Interface(iface = "li.cil.oc.api.network.Environment", modid = "opencomputers"),
])
abstract class ComputerPeripheralBlockEntity extends TileEntity implements IPeripheral, ManagedPeripheral, Environment {

    protected ComputerPeripheralBlockEntity() {
        if (Loader.isModLoaded("opencomputers")) {
            ocInit()
        }
    }

    abstract String getPeripheralName();

    protected abstract API getPeripheralAPI();

    LuaObjectProxy getPeripheralAPIProxy() {
        return new LuaObjectProxy(getPeripheralAPI())
    }

    void onLoad() {
        super.onLoad()
        if (Loader.isModLoaded("opencomputers")) {
            ocOnLoad()
        }
    }

    void onChunkUnload() {
        super.onChunkUnload()
        if (Loader.isModLoaded("opencomputers")) {
            ocOnChunkUnload()
        }
    }

    void invalidate() {
        super.invalidate()
        if (Loader.isModLoaded("opencomputers")) {
            ocInvalidate()
        }
    }

    void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt)
        if (Loader.isModLoaded("opencomputers")) {
            ocReadFromNBT(nbt)
        }
    }

    NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt)
        if (Loader.isModLoaded("opencomputers")) {
            ocWriteToNBT(nbt)
        }
        return nbt
    }

    /* ComputerCraft */

    @Optional.Method(modid = "computercraft")
    private ComputerCraftObjectProxy getPeripheralCCProxy() {
        return new ComputerCraftObjectProxy(getPeripheralAPIProxy())
    }

    @Optional.Method(modid = "computercraft")
    @Nonnull
    @Override
    String getType() {
        return getPeripheralName()
    }

    @Optional.Method(modid = "computercraft")
    @Nonnull
    @Override
    String[] getMethodNames() {
        return getPeripheralCCProxy().getMethodNames()
    }

    @Optional.Method(modid = "computercraft")
    @Nullable
    @Override
    Object[] callMethod(@Nonnull IComputerAccess computerAccess, @Nonnull ILuaContext context, int methodIndex, @Nonnull Object[] arguments) throws LuaException, InterruptedException {
        try {
            if (computerAccess.getClass().getPackage().getName().startsWith("li.cil.oc")) {
                //TODO for some reason methods, found on both CC and OC, it seems the CC version hasAPIFor priority over OC methods when called from OC. Maybe because of use of OC ManagedPeripheral
                throw new RuntimeException("CC called from OC")
            }

            def result = getPeripheralCCProxy().callMethod(context, methodIndex, arguments)
            return result
        } catch (Throwable e) {
            e.printStackTrace()
            throw e
        }
    }

    @Optional.Method(modid = "computercraft")
    @Override
    boolean equals(@Nullable IPeripheral iPeripheral) {
        return iPeripheral == this
    }

    /* OpenComputers */

    private static final String OC_TAG_NODE = "oc:node"
    private Object ocNode

    @Optional.Method(modid = "opencomputers")
    void ocInit() {
        ocNode = Network.newNode(this, Visibility.Network).withComponent(getPeripheralName(), Visibility.Network).create()
    }

    @Optional.Method(modid = "opencomputers")
    private OpenComputersObjectProxy getPeripheralOCProxy() {
        return new OpenComputersObjectProxy(getPeripheralAPIProxy())
    }

    @Optional.Method(modid = "opencomputers")
    @Override
    String[] methods() {
        return getPeripheralOCProxy().methods()
    }

    @Optional.Method(modid = "opencomputers")
    @Override
    Object[] invoke(String method, Context context, Arguments arguments) throws Exception {
        def result = getPeripheralOCProxy().invoke(method, context, arguments)
        return result
    }

    @Optional.Method(modid = "opencomputers")
    @Override
    Node node() {
        return (Node) ocNode
    }

    @Optional.Method(modid = "opencomputers")
    @Override
    void onConnect(Node node) {
    }

    @Optional.Method(modid = "opencomputers")
    @Override
    void onDisconnect(Node node) {
    }

    @Optional.Method(modid = "opencomputers")
    @Override
    void onMessage(Message message) {
    }

    @Optional.Method(modid = "opencomputers")
    void ocOnLoad() {
        Network.joinOrCreateNetwork(this)
    }

    @Optional.Method(modid = "opencomputers")
    void ocOnChunkUnload() {
        node()?.remove()
    }

    @Optional.Method(modid = "opencomputers")
    void ocInvalidate() {
        node()?.remove()
    }

    @Optional.Method(modid = "opencomputers")
    void ocReadFromNBT(NBTTagCompound nbt) {
        if (node()?.host() == this) {
            node().load(nbt.getCompoundTag(OC_TAG_NODE))
        }
    }

    @Optional.Method(modid = "opencomputers")
    NBTTagCompound ocWriteToNBT(NBTTagCompound nbt) {
        if (node()?.host() == this) {
            NBTTagCompound nodeNbt = new NBTTagCompound()
            node().save(nodeNbt)
            nbt.setTag(OC_TAG_NODE, nodeNbt)
        }
        return nbt
    }

}
