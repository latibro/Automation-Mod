package latibro.automation.interfacebox

import latibro.automation.core.peripheral.PeripheralTileEntity

class InterfaceBoxTileEntity extends PeripheralTileEntity {

    @Override
    String getComponentName() {
        return "interface_box"
    }

    @Override
    protected InterfaceBoxAPI getPeripheralAPI() {
        return new InterfaceBoxAPIImpl(this)
    }

}
