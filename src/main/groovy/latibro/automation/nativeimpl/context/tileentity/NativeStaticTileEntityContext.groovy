package latibro.automation.nativeimpl.context.tileentity

import latibro.automation.core.context.CoreContext
import net.minecraft.tileentity.TileEntity

final class NativeStaticTileEntityContext extends AbstractNativeTileEntityContext implements CoreContext {

    private final TileEntity nativeTileEntity

    NativeStaticTileEntityContext(TileEntity nativeTileEntity) {
        this.nativeTileEntity = nativeTileEntity
    }

    TileEntity getNativeTileEntity() {
        return nativeTileEntity
    }

}
