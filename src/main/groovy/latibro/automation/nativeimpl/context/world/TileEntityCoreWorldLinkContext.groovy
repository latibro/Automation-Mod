package latibro.automation.nativeimpl.context.world

import latibro.automation.core.LinkType
import latibro.automation.nativeimpl.context.tileentity.CoreTileEntityLinkContext
import net.minecraft.world.World

class TileEntityCoreWorldLinkContext extends CoreWorldLinkContext {

    private final CoreTileEntityLinkContext tileEntity

    TileEntityCoreWorldLinkContext(CoreTileEntityLinkContext tileEntity) {
        this.tileEntity = Objects.requireNonNull(tileEntity)
    }

    @Override
    World getNativeWorld() {
        return tileEntity.nativeTileEntity.world
    }

    @Override
    LinkType getLinkType() {
        return LinkType.DYNAMIC
    }

}
