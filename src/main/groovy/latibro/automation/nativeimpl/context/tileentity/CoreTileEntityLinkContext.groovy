package latibro.automation.nativeimpl.context.tileentity

import latibro.automation.core.context.CoreContext
import latibro.automation.core.context.tileentity.TileEntityLinkContext
import latibro.automation.nativeimpl.context.location.CoreLocationLinkContext
import latibro.automation.nativeimpl.context.location.TileEntityCoreLocationLinkContext
import net.minecraft.tileentity.TileEntity

abstract class CoreTileEntityLinkContext implements TileEntityLinkContext, CoreContext {

    abstract TileEntity getNativeTileEntity()

    @Override
    boolean isLinked() {
        return nativeTileEntity
    }

    @Override
    CoreLocationLinkContext getLocation() {
        return new TileEntityCoreLocationLinkContext(this)
    }

    @Override
    boolean isLoaded() {
        return location.isLoaded()
    }

    @Override
    String getType() {
        return nativeTileEntity.getBlockType().getRegistryName().toString()
    }

}
