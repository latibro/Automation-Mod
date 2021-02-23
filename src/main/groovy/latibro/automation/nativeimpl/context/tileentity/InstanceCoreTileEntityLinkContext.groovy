package latibro.automation.nativeimpl.context.tileentity

import latibro.automation.core.LinkType
import net.minecraft.tileentity.TileEntity

final class InstanceCoreTileEntityLinkContext extends CoreTileEntityLinkContext {

    private final TileEntity nativeTileEntity

    InstanceCoreTileEntityLinkContext(TileEntity nativeTileEntity) {
        this.nativeTileEntity = nativeTileEntity
    }

    TileEntity getNativeTileEntity() {
        return nativeTileEntity
    }

    @Override
    LinkType getLinkType() {
        return LinkType.STATIC
    }

}
