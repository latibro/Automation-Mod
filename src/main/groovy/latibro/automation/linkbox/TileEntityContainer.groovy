package latibro.automation.linkbox


import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.SlotItemHandler

abstract class TileEntityContainer extends PlayerContainer {

    static class TileEntitySlot extends SlotItemHandler {

        private final TileEntity tileEntity

        TileEntitySlot(TileEntity tileEntity, int index, int xPosition, int yPosition) {
            super(tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH), index, xPosition, yPosition)
            this.tileEntity = tileEntity
        }

        @Override
        void onSlotChanged() {
            tileEntity.markDirty()
        }

    }

}
