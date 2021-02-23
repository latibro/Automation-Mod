package latibro.automation.nativeimpl.context.tileentity

import latibro.automation.core.context.CoreContext
import latibro.automation.core.context.tileentity.TileEntityLinkContext
import latibro.automation.nativeimpl.context.location.CoreLocationContext
import latibro.automation.nativeimpl.context.location.TileEntityCoreLocationContext
import latibro.automation.nativeimpl.context.world.CoreWorldLinkContext
import latibro.automation.nativeimpl.context.world.TileEntityCoreWorldLinkContext
import net.minecraft.tileentity.TileEntity

abstract class CoreTileEntityLinkContext implements TileEntityLinkContext, CoreContext {

    abstract TileEntity getNativeTileEntity()

    @Override
    boolean isLinked() {
        return nativeTileEntity
    }

    @Override
    CoreWorldLinkContext getWorld() {
        return new TileEntityCoreWorldLinkContext(this)
    }

    @Override
    CoreLocationContext getLocation() {
        return new TileEntityCoreLocationContext(this)
    }

}
