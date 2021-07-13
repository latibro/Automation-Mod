package latibro.automation.computer.relaybox.block

import groovy.transform.CompileStatic
import latibro.automation.api.API
import latibro.automation.computer.peripheral.ComputerPeripheralBlockEntity
import latibro.automation.computer.relaybox.ComputerRelayBoxContext
import latibro.automation.computer.relaybox.ComputerRelayBoxLuaAPI
import latibro.automation.core.source.card.item.SourceCardItem
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.ItemStackHandler

import javax.annotation.Nullable

@CompileStatic
class ComputerRelayBoxBlockEntity extends ComputerPeripheralBlockEntity {

    private final ItemStackHandler inventory

    ComputerRelayBoxBlockEntity() {
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
    String getPeripheralName() {
        return "automation-relay"
    }

    @Override
    protected API getPeripheralAPI() {
        def context = ComputerRelayBoxContext.create(this)
        return new ComputerRelayBoxLuaAPI(context)
    }

    Object getSourceObject() {
        def itemStack = inventory.getStackInSlot(0)

        if (!itemStack) {
            return null
        }

        def item = itemStack.item

        if (item instanceof SourceCardItem) {
            return item.getSourceObject(itemStack)
        }

        return null
    }

    ItemStack getSourceCard(int index) {
        def itemStack = inventory.getStackInSlot(index)

        if (!itemStack) {
            return null
        }

        def item = itemStack.item

        if (item instanceof SourceCardItem) {
            return itemStack
        }

        return null
    }

}
