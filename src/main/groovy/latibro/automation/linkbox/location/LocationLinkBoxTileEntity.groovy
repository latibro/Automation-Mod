package latibro.automation.linkbox.location

import groovy.transform.CompileStatic
import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.location.LocationAPI
import latibro.automation.core.peripheral.PeripheralTileEntity
import latibro.automation.nativeimpl.context.location.NativeStaticLocationContext
import latibro.automation.nativeimpl.context.tileentity.NativeStaticTileEntityContext
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.ItemStackHandler

import javax.annotation.Nullable

@CompileStatic
class LocationLinkBoxTileEntity extends PeripheralTileEntity {

    private final ItemStackHandler inventory

    LocationLinkBoxTileEntity() {
        inventory = new ItemStackHandler(1)
    }

    @Override
    NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("inventory", inventory.serializeNBT())
        return super.writeToNBT(compound)
    }

    @Override
    void readFromNBT(NBTTagCompound compound) {
        inventory.deserializeNBT(compound.getCompoundTag("inventory"))
        super.readFromNBT(compound)
    }

    @Override
    boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing)
    }

    @Nullable
    @Override
    <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)inventory : super.getCapability(capability, facing)
    }

    @Override
    String getComponentName() {
        return "location_link"
    }

    BlockPos getLocationBlockPos() {
        def itemStack = inventory.getStackInSlot(0)
        //TODO register some check if there is items in the stack, and it hasAPIFor a uuid
        return BlockPos.fromLong(itemStack.getTagCompound().getLong("location"))
    }

    @Override
    protected LocationAPI getPeripheralAPI() {
        def tileEntityContext = new NativeStaticTileEntityContext(this)
        //TODO make the link to xyz dynamic
        def entityContext = new NativeStaticLocationContext(getLocationBlockPos(), tileEntityContext.worldContext)
        return APIRegistry.getAPI(entityContext) as LocationAPI
    }

}
