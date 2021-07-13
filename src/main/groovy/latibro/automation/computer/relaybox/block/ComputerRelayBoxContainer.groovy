package latibro.automation.computer.relaybox.block

import groovy.transform.CompileStatic
import latibro.automation.linkbox.TileEntityContainer
import net.minecraft.entity.player.EntityPlayer

@CompileStatic
class ComputerRelayBoxContainer extends TileEntityContainer {

    ComputerRelayBoxContainer(ComputerRelayBoxBlockEntity blockEntity, EntityPlayer player) {
        addSlotToContainer(new TileEntitySlot(blockEntity, 0, 80, 35))

        addPlayerSlotsToContainer(player, 8, 84)
    }

    @Override
    boolean canInteractWith(EntityPlayer player) {
        return true
    }

}
