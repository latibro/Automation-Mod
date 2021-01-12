package latibro.automation.core.peripheral

import groovy.transform.CompileStatic
import latibro.automation.core.api.APIHost
import latibro.automation.core.api.HostedAPI
import latibro.automation.core.lua.LuaObjectProxy
import latibro.automation.integration.computercraft.CCPeripheralTrait
import latibro.automation.integration.opencomputers.OCTileEntityEnvironmentTrait
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.Optional

@CompileStatic
@Optional.InterfaceList([
    @Optional.Interface(iface = "latibro.automation.integration.opencomputers.OCTileEntityEnvironmentTrait", modid = "opencomputers"),
    @Optional.Interface(iface = "latibro.automation.integration.computercraft.CCPeripheralTrait", modid = "computercraft"),
])
abstract class PeripheralTileEntity extends TileEntity implements OCTileEntityEnvironmentTrait, CCPeripheralTrait, Peripheral, APIHost {

    protected PeripheralTileEntity() {
        if (Loader.isModLoaded("opencomputers")) {
            OCTileEntityEnvironmentTrait.super.init()
        }
    }

    TileEntity getSelf() {
        return this
    }

    abstract String getComponentName();

    protected abstract HostedAPI getPeripheralAPI();

    @Override
    World getMinecraftWorld() {
        return getWorld()
    }

    LuaObjectProxy getPeripheralAPIProxy() {
        return new LuaObjectProxy(getPeripheralAPI())
    }

    void onLoad() {
        super.onLoad()
        if (Loader.isModLoaded("opencomputers")) {
            OCTileEntityEnvironmentTrait.super.onLoad()
        }
    }

    void onChunkUnload() {
        super.onChunkUnload();
        if (Loader.isModLoaded("opencomputers")) {
            OCTileEntityEnvironmentTrait.super.onChunkUnload()
        }
    }

    void invalidate() {
        super.invalidate();
        if (Loader.isModLoaded("opencomputers")) {
            OCTileEntityEnvironmentTrait.super.invalidate()
        }
    }

    void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (Loader.isModLoaded("opencomputers")) {
            OCTileEntityEnvironmentTrait.super.readFromNBT(nbt)
        }
    }

    NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt)
        if (Loader.isModLoaded("opencomputers")) {
            OCTileEntityEnvironmentTrait.super.writeToNBT(nbt)
        }
        return nbt;
    }

}
