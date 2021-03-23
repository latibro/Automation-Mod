package latibro.automation.linkbox.data

import groovy.transform.CompileStatic
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container

@CompileStatic
class DataBoxContainer extends Container {

    private final DataBoxTileEntity tileEntity

    DataBoxContainer(DataBoxTileEntity tileEntity) {
        this.tileEntity = tileEntity
    }

    @Override
    boolean canInteractWith(EntityPlayer player) {
        return true
    }

    String getData() {
        return tileEntity.getData()
    }

    void setData(String data) {
        tileEntity.setData(data)
    }

}
