package latibro.automation.interfacebox


import latibro.automation.core.peripheral.PeripheralTileEntity

class InterfaceBoxTileEntity extends PeripheralTileEntity {

    InterfaceBoxTileEntity() {
    }

    String getComponentName() {
        return "interface_box"
    }

    protected InterfaceBoxAPI getPeripheralAPI() {
        return new InterfaceBoxAPIImpl(this)
    }

}
