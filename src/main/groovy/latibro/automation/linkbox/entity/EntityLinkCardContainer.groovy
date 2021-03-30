package latibro.automation.linkbox.entity

import groovy.transform.CompileStatic
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container

@CompileStatic
class EntityLinkCardContainer extends Container {

    private final EntityPlayer player

    EntityLinkCardContainer(EntityPlayer player) {
        this.player = player
    }

    @Override
    boolean canInteractWith(EntityPlayer player) {
        return true
    }

    UUID getEntityUUID() {
        return EntityLinkCardItem.getEntityUUID(player)
    }

    void setEntityUUID(UUID entityUuid) {
        EntityLinkCardItem.setEntityUUID(player, entityUuid)
        detectAndSendChanges()
    }

}
