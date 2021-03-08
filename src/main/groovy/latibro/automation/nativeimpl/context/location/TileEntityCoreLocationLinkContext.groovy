package latibro.automation.nativeimpl.context.location

import groovy.transform.CompileStatic
import latibro.automation.core.LinkType
import latibro.automation.nativeimpl.context.tileentity.CoreTileEntityLinkContext
import latibro.automation.nativeimpl.context.world.CoreWorldLinkContext
import net.minecraft.util.math.BlockPos

@CompileStatic
final class TileEntityCoreLocationLinkContext extends CoreLocationLinkContext {

    private final CoreTileEntityLinkContext tileEntity

    TileEntityCoreLocationLinkContext(CoreTileEntityLinkContext tileEntity) {
        this.tileEntity = Objects.requireNonNull(tileEntity)
    }

    @Override
    BlockPos getNativeLocation() {
        return tileEntity.nativeTileEntity.pos
    }

    @Override
    CoreWorldLinkContext getWorld() {
        return tileEntity.location.world
    }

    @Override
    LinkType getLinkType() {
        return LinkType.DYNAMIC
    }

}
