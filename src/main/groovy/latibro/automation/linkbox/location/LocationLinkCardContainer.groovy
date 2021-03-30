package latibro.automation.linkbox.location

import groovy.transform.CompileStatic
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container
import net.minecraft.util.math.BlockPos

@CompileStatic
class LocationLinkCardContainer extends Container {

    private final EntityPlayer player

    LocationLinkCardContainer(EntityPlayer player) {
        this.player = player
    }

    @Override
    boolean canInteractWith(EntityPlayer player) {
        return true
    }

    BlockPos getLocation() {
        return LocationLinkCardItem.getLocation(player)
    }

    void setLocation(BlockPos location) {
        LocationLinkCardItem.setLocation(player, location)
        detectAndSendChanges()
    }

}
