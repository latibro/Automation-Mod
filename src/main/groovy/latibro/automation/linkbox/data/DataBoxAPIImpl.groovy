package latibro.automation.linkbox.data

import latibro.automation.core.api.tileentity.BaseTileEntityLinkAPI
import latibro.automation.nativeimpl.context.tileentity.CoreTileEntityLinkContext

class DataBoxAPIImpl extends BaseTileEntityLinkAPI<CoreTileEntityLinkContext> implements DataBoxAPI {

    DataBoxAPIImpl(CoreTileEntityLinkContext context) {
        super(context)
    }

    DataBoxTileEntity getDataBoxTileEntity() {
        return (DataBoxTileEntity) context.nativeTileEntity
    }

    @Override
    String getData() {
        return dataBoxTileEntity.getData()
    }

    @Override
    void setData(String data) {
        dataBoxTileEntity.setData(data)
    }

}
