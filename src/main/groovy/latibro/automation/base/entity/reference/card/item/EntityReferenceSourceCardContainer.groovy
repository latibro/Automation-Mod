package latibro.automation.base.entity.reference.card.item

import groovy.transform.CompileStatic
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container
import net.minecraft.item.ItemStack

@CompileStatic
class EntityReferenceSourceCardContainer extends Container {

    private final Container inventory
    private final ItemStack itemStack

    EntityReferenceSourceCardContainer(Container inventory, ItemStack itemStack) {
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

    private EntityReferenceSourceCardItem getItem() {
        return (EntityReferenceSourceCardItem) itemStack.item
    }

    String getEntityUuid() {
        return item.getReferenceEntityUuid(itemStack)
    }

    void setEntityUuid(String uuid) {
        item.setReferenceEntityUuid(itemStack, uuid)
    }

}
