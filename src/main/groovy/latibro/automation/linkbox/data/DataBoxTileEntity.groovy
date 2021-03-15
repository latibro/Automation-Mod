package latibro.automation.linkbox.data

import groovy.transform.CompileStatic
import latibro.automation.core.peripheral.PeripheralTileEntity
import latibro.automation.nativeimpl.context.tileentity.InstanceCoreTileEntityLinkContext

@CompileStatic
class DataBoxTileEntity extends PeripheralTileEntity {

    @Override
    String getComponentName() {
        return "data_box"
    }

    @Override
    protected DataBoxAPI getPeripheralAPI() {
        def tileEntityContext = new InstanceCoreTileEntityLinkContext(this)
        def dataBoxAPI = new DataBoxAPIImpl(tileEntityContext)
        return dataBoxAPI
    }

    String getData() {
        return getTileData().getString("data")
    }

    void setData(String data) {
        getTileData().setString("data", data)
    }

}
