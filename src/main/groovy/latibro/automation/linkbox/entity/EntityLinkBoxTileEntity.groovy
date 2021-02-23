package latibro.automation.linkbox.entity

import groovy.transform.CompileStatic
import latibro.automation.api.link.entity.EntityLinkAPI
import latibro.automation.core.api.APIRegistry
import latibro.automation.core.peripheral.PeripheralTileEntity
import latibro.automation.nativeimpl.context.entity.UUIDCoreEntityLinkContext
import latibro.automation.nativeimpl.context.server.CoreServerLinkContext
import latibro.automation.nativeimpl.context.tileentity.InstanceCoreTileEntityLinkContext
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
        //TODO register some check if there is items in the stack, and it hasAPIFor a uuid
        return UUID.fromString(itemStack.getTagCompound().getString("entityUUID"))
    }

    @Override
    protected EntityLinkAPI getPeripheralAPI() {
        def tileEntityLink = new InstanceCoreTileEntityLinkContext(this)
        //TODO make the link to UUID dynamic
        def entityContext = new UUIDCoreEntityLinkContext(getEntityUUID(), tileEntityLink.world.server as CoreServerLinkContext)
        return APIRegistry.getAPI(entityContext) as EntityLinkAPI
    }

}
