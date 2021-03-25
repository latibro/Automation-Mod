package latibro.automation.linkbox.redstone

import groovy.transform.CompileStatic
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container

@CompileStatic
class RedstoneBoxContainer extends Container {

    private final RedstoneBoxTileEntity tileEntity

    RedstoneBoxContainer(RedstoneBoxTileEntity tileEntity) {
        this.tileEntity = tileEntity
    }

    @Override
    boolean canInteractWith(EntityPlayer player) {
        return true
    }

    int getPowerLevel() {
        return tileEntity.getPowerLevel()
    }

    void setPowerLevel(int level) {
        tileEntity.setPowerLevel(level)
    }

}
