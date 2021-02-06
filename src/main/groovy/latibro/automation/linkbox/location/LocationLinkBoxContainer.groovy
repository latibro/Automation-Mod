package latibro.automation.linkbox.location

import groovy.transform.CompileStatic
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.Container
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.SlotItemHandler

@CompileStatic
class LocationLinkBoxContainer extends Container {

    LocationLinkBoxContainer(InventoryPlayer playerInv, final LocationLinkBoxTileEntity locationLinkBox) {
        IItemHandler inventory = locationLinkBox.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH)

        addSlotToContainer(new SlotItemHandler(inventory, 0, 80, 35) {
            @Override
            void onSlotChanged() {
                locationLinkBox.markDirty()
            }
        })

        // Player inventory
        for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
            int yPosition = 84 + (rowIndex * 18)
            for (int slotIndex = 0; slotIndex < 9; slotIndex++) {
                int xPosition = 8 + (slotIndex * 18)
                int index = slotIndex + (rowIndex * 9) + 9
                addSlotToContainer(new Slot(playerInv, index, xPosition, yPosition))
            }
        }

        // Player hotbar
        for (int slotIndex = 0; slotIndex < 9; slotIndex++) {
            int xPosition = 8 + (slotIndex * 18)
            addSlotToContainer(new Slot(playerInv, slotIndex, xPosition, 142))
        }
    }

    @Override
    boolean canInteractWith(EntityPlayer playerIn) {
        return true
    }

    @Override
    ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack itemstack = ItemStack.EMPTY
        Slot slot = inventorySlots.get(index)

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack()
            itemstack = itemstack1.copy()

            int containerSlots = inventorySlots.size() - player.inventory.mainInventory.size()

            if (index < containerSlots) {
                if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true)) {
                    return ItemStack.EMPTY
                }
            } else if (!this.mergeItemStack(itemstack1, 0, containerSlots, false)) {
                return ItemStack.EMPTY
            }

            if (itemstack1.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY)
            } else {
                slot.onSlotChanged()
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY
            }

            slot.onTake(player, itemstack1)
        }

        return itemstack
    }

}
