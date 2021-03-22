package latibro.automation.linkbox.entity

import groovy.transform.CompileStatic
import latibro.automation.linkbox.TileEntityContainer
import net.minecraft.entity.player.EntityPlayer

@CompileStatic
class EntityLinkBoxContainer extends TileEntityContainer {

    EntityLinkBoxContainer(EntityLinkBoxTileEntity tileEntity, EntityPlayer player) {
        addSlotToContainer(new TileEntitySlot(tileEntity, 0, 80, 35))

        addPlayerSlotsToContainer(player, 8, 84)
    }

    @Override
    boolean canInteractWith(EntityPlayer player) {
        return true
    }

}
