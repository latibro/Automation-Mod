package latibro.automation.interfacebox

import latibro.automation.core.api.API
import latibro.automation.core.peripheral.PeripheralTileEntity

class InterfaceBoxTileEntity extends PeripheralTileEntity {

    InterfaceBoxTileEntity() {
    }

    protected String getComponentName() {
        return "interface_box"
    }

    protected API getPeripheralAPI() {
        return new InterfaceBox(this)
    }

}
