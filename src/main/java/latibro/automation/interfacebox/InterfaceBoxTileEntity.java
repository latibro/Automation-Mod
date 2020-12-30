package latibro.automation.interfacebox;

import latibro.automation.core.api.API;
import latibro.automation.core.peripheral.PeripheralTileEntity;

public class InterfaceBoxTileEntity extends PeripheralTileEntity {

    public InterfaceBoxTileEntity() {
    }

    protected String getComponentName() {
        return "interface_box";
    }

    protected API getPeripheralAPI() {
        return new InterfaceBox(this);
    }

}
