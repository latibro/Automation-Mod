package latibro.automation.interfacebox

import latibro.automation.nativeimpl.context.tileentity.AbstractNativeTileEntityContext
import latibro.automation.core.peripheral.PeripheralTileEntity
import net.minecraft.tileentity.TileEntity

class InterfaceBoxTileEntity extends PeripheralTileEntity {

    @Override
    String getComponentName() {
        return "interface_box"
    }

    @Override
    protected InterfaceBoxAPI getPeripheralAPI() {
        def context = new AbstractNativeTileEntityContext() {
            @Override
            TileEntity getNativeTileEntity() {
                return self
            }

            @Override
            InterfaceBoxAPI getAPI() {
                return new InterfaceBoxAPIImpl(this)
            }
        }

        return context.getAPI()
    }

}
