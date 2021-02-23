package latibro.automation.linkbox.location

import groovy.transform.CompileStatic
import latibro.automation.api.link.location.LocationLinkAPI
import latibro.automation.core.LinkType
import latibro.automation.core.api.APIRegistry
import latibro.automation.core.peripheral.PeripheralTileEntity
import latibro.automation.nativeimpl.context.location.CoreLocationLinkContext
import latibro.automation.nativeimpl.context.tileentity.InstanceCoreTileEntityLinkContext
import latibro.automation.nativeimpl.context.world.CoreWorldLinkContext
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
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) inventory : super.getCapability(capability, facing)
    }

    @Override
    String getComponentName() {
        return "location_link"
    }

    BlockPos getLocationBlockPos() {
        def itemStack = inventory.getStackInSlot(0)
        if (!itemStack || itemStack.isEmpty()) {
            return null
        }
        def locationLong = itemStack.getTagCompound()?.getLong("location")
        if (!locationLong) {
            return null
        }
        return BlockPos.fromLong(locationLong)
    }

    @Override
    protected LocationLinkAPI getPeripheralAPI() {
        def tileEntityContext = new InstanceCoreTileEntityLinkContext(this)
        def linkBox = this
        def linkContext = new CoreLocationLinkContext() {

            @Override
            LinkType getLinkType() {
                return LinkType.DYNAMIC
            }

            @Override
            BlockPos getNativeLocation() {
                return linkBox.getLocationBlockPos()
            }

            @Override
            CoreWorldLinkContext getWorld() {
                return tileEntityContext.getWorld()
            }

        }
        def linkAPI = APIRegistry.getAPI(linkContext) as LocationLinkAPI
        return linkAPI
    }

}
