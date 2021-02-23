package latibro.automation.nativeimpl.context.world

import groovy.transform.CompileStatic
import latibro.automation.core.LinkType
import latibro.automation.nativeimpl.context.server.CoreServerLinkContext
import latibro.automation.nativeimpl.context.server.TileEntityCoreServerLinkContext
import latibro.automation.nativeimpl.context.tileentity.CoreTileEntityLinkContext
import net.minecraft.world.World

@CompileStatic
final class TileEntityCoreWorldLinkContext extends CoreWorldLinkContext {

    private final CoreTileEntityLinkContext tileEntity

    TileEntityCoreWorldLinkContext(CoreTileEntityLinkContext tileEntity) {
        this.tileEntity = Objects.requireNonNull(tileEntity)
    }

    World getNativeWorld() {
        return tileEntity.nativeTileEntity.world
    }

    @Override
    CoreServerLinkContext getServer() {
        return new TileEntityCoreServerLinkContext(tileEntity)
    }

    @Override
    LinkType getLinkType() {
        return LinkType.DYNAMIC
    }

}