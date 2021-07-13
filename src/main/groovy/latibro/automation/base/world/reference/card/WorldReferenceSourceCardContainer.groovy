package latibro.automation.base.world.reference.card

import groovy.transform.CompileStatic
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container
import net.minecraft.item.ItemStack

@CompileStatic
class WorldReferenceSourceCardContainer extends Container {

    private final Container inventory
    private final ItemStack itemStack

    WorldReferenceSourceCardContainer(Container inventory, ItemStack itemStack) {
        //TODO check that itemStack is found in inventory
        this.inventory = inventory
        this.itemStack = itemStack
    }

    @Override
    boolean canInteractWith(EntityPlayer player) {
        return inventory.canInteractWith(player)
    }

    @Override
    void detectAndSendChanges() {
        super.detectAndSendChanges()
        inventory.detectAndSendChanges()
    }

    private WorldReferenceSourceCardItem getItem() {
        return (WorldReferenceSourceCardItem) itemStack.item
    }

    void setWorldName(String worldName) {
        item.setReferenceWorldName(itemStack, worldName)
    }

    String getWorldName() {
        return item.getReferenceWorldName(itemStack)
    }

}
