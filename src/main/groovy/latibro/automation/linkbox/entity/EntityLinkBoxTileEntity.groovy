package latibro.automation.linkbox.entity

import groovy.transform.CompileStatic
import latibro.automation.api.link.entity.EntityLinkAPI
import latibro.automation.core.LinkType
import latibro.automation.core.api.APIRegistry
import latibro.automation.core.peripheral.PeripheralTileEntity
import latibro.automation.nativeimpl.context.entity.CoreEntityLinkContext
import net.minecraft.entity.Entity
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.ItemStackHandler

import javax.annotation.Nullable

@CompileStatic
class EntityLinkBoxTileEntity extends PeripheralTileEntity {

    private final ItemStackHandler inventory

    EntityLinkBoxTileEntity() {
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
        return "entity_link"
    }

    UUID getEntityUUID() {
        def itemStack = inventory.getStackInSlot(0)
        if (!itemStack || itemStack.isEmpty()) {
            return null
        }
        return EntityLinkCardItem.getEntityUUID(itemStack)
    }

    @Override
    protected EntityLinkAPI getPeripheralAPI() {
        def linkBox = this
        def linkContext = new CoreEntityLinkContext() {

            @Override
            LinkType getLinkType() {
                return LinkType.DYNAMIC
            }

            Entity getNativeEntity() {
                return linkBox.world.minecraftServer.getEntityFromUuid(linkBox.getEntityUUID())
            }

            @Override
            UUID getUUID() {
                return linkBox.getEntityUUID()
            }

        }
        def linkAPI = APIRegistry.getAPI(linkContext) as EntityLinkAPI
        return linkAPI
    }

}
