package latibro.automation.base.location.block.reference.card

import groovy.transform.CompileStatic
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container
import net.minecraft.item.ItemStack

@CompileStatic
class BlockLocationReferenceSourceCardContainer extends Container {

    private final Container inventory
    private final ItemStack itemStack

    BlockLocationReferenceSourceCardContainer(Container inventory, ItemStack itemStack) {
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

    private BlockLocationReferenceSourceCardItem getItem() {
        return (BlockLocationReferenceSourceCardItem) itemStack.item
    }

    void setWorldName(String worldName) {
        item.setReferenceWorldName(itemStack, worldName)
    }

    String getWorldName() {
        return item.getReferenceWorldName(itemStack)
    }

    void setCoordinateX(Integer coordinate) {
        item.setReferenceCoordinateX(itemStack, coordinate)
    }

    String getCoordinateX() {
        return item.getReferenceCoordinateX(itemStack)
    }

    void setCoordinateY(Integer coordinate) {
        item.setReferenceCoordinateY(itemStack, coordinate)
    }

    String getCoordinateY() {
        return item.getReferenceCoordinateY(itemStack)
    }

    void setCoordinateZ(Integer coordinate) {
        item.setReferenceCoordinateZ(itemStack, coordinate)
    }

    String getCoordinateZ() {
        return item.getReferenceCoordinateZ(itemStack)
    }

}
