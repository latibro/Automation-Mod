package latibro.automation.linkbox.location

import groovy.transform.CompileStatic
import latibro.automation.linkbox.TileEntityContainer
import net.minecraft.entity.player.EntityPlayer

@CompileStatic
class LocationLinkBoxContainer extends TileEntityContainer {

    LocationLinkBoxContainer(EntityPlayer player, LocationLinkBoxTileEntity tileEntity) {
        addSlotToContainer(new TileEntitySlot(tileEntity, 0, 80, 35))

        addPlayerSlotsToContainer(player, 8, 84)
    }

    @Override
    boolean canInteractWith(EntityPlayer playerIn) {
        return true
    }

}
