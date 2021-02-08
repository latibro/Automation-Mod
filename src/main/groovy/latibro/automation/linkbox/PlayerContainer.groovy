package latibro.automation.linkbox

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack

abstract class PlayerContainer extends Container {

    List<PlayerHotbarSlot> addPlayerHotbarSlotsToContainer(EntityPlayer player, int xPosition, int yPosition) {
        List<PlayerHotbarSlot> slots = []

        for (int slotIndex = 0; slotIndex < 9; slotIndex++) {
            int slotX = xPosition + (slotIndex * 18)
            int inventoryIndex = slotIndex
            def slot = new PlayerHotbarSlot(player, inventoryIndex, slotX, yPosition)
            addSlotToContainer(slot)
            slots.add(slot)
        }

        return slots
    }

    List<PlayerSlot> addPlayerSlotsToContainer(EntityPlayer player, int xPosition, int yPosition) {
        List<PlayerSlot> slots = []

        // Player hotbar
        def hotbarSlots = addPlayerHotbarSlotsToContainer(player, xPosition, yPosition + 58)
        slots.addAll(hotbarSlots)

        // Player inventory
        for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
            int slotY = yPosition + (rowIndex * 18)
            for (int slotIndex = 0; slotIndex < 9; slotIndex++) {
                int slotX = xPosition + (slotIndex * 18)
                int inventoryIndex = slotIndex + (rowIndex * 9) + 9
                def slot = new PlayerSlot(player, inventoryIndex, slotX, slotY)
                addSlotToContainer(slot)
                slots.add(slot)
            }
        }

        return slots
    }

    @Override
    ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        //TODO enable shift click move
        return ItemStack.EMPTY
    }

    static class PlayerSlot extends Slot {

        private final EntityPlayer player

        PlayerSlot(EntityPlayer player, int index, int xPosition, int yPosition) {
            super(player.inventory, index, xPosition, yPosition)
            this.player = player
        }

    }

    static class PlayerHotbarSlot extends PlayerSlot {

        PlayerHotbarSlot(EntityPlayer player, int index, int xPosition, int yPosition) {
            super(player, index, xPosition, yPosition)
        }

    }

}
