package latibro.automation.linkbox.data

import latibro.automation.core.api.tileentity.BaseTileEntityLinkAPI
import latibro.automation.nativeimpl.context.tileentity.CoreTileEntityLinkContext

class DataBoxAPIImpl extends BaseTileEntityLinkAPI<CoreTileEntityLinkContext> implements DataBoxAPI {

    DataBoxAPIImpl(CoreTileEntityLinkContext context) {
        super(context)
    }

    DataBoxTileEntity getTileEntity() {
        return (DataBoxTileEntity) context.nativeTileEntity
    }

    @Override
    String getData() {
        return tileEntity.getData()
    }

    @Override
    void setData(String data) {
        tileEntity.setData(data)
    }

}
