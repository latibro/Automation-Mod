package latibro.automation.nativeimpl.context.location

import groovy.transform.CompileStatic
import latibro.automation.core.LinkType
import latibro.automation.nativeimpl.context.tileentity.CoreTileEntityLinkContext
import latibro.automation.nativeimpl.context.world.CoreWorldLinkContext
import net.minecraft.util.math.BlockPos

@CompileStatic
final class TileEntityCoreLocationContext extends CoreLocationContext {

    private final CoreTileEntityLinkContext tileEntity

    TileEntityCoreLocationContext(CoreTileEntityLinkContext tileEntity) {
        this.tileEntity = Objects.requireNonNull(tileEntity)
    }

    @Override
    BlockPos getNativeLocation() {
        return tileEntity.nativeTileEntity.pos
    }

    @Override
    CoreWorldLinkContext getWorld() {
        return tileEntity.world
    }

    @Override
    LinkType getLinkType() {
        return LinkType.DYNAMIC
    }

}
