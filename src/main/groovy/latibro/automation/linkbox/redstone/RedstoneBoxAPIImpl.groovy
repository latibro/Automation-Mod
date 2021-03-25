package latibro.automation.linkbox.redstone

import latibro.automation.core.api.tileentity.BaseTileEntityLinkAPI
import latibro.automation.nativeimpl.context.tileentity.CoreTileEntityLinkContext

class RedstoneBoxAPIImpl extends BaseTileEntityLinkAPI<CoreTileEntityLinkContext> implements RedstoneBoxAPI {

    RedstoneBoxAPIImpl(CoreTileEntityLinkContext context) {
        super(context)
    }

    RedstoneBoxTileEntity getTileEntity() {
        return (RedstoneBoxTileEntity) context.nativeTileEntity
    }

    @Override
    Number getPowerLevel() {
        return tileEntity.getPowerLevel()
    }

    @Override
    void setPowerLevel(Number level) {
        tileEntity.setPowerLevel(level as int)
    }

}
