package latibro.automation.linkbox.data

import groovy.transform.CompileStatic
import latibro.automation.api.API
import latibro.automation.core.peripheral.PeripheralTileEntity

@CompileStatic
class DataBoxTileEntity extends PeripheralTileEntity {

    @Override
    String getComponentName() {
        return "data_box"
    }

    @Override
    protected API getPeripheralAPI() {
        return null
    }

}
