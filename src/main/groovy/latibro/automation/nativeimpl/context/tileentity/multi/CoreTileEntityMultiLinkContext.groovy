package latibro.automation.nativeimpl.context.tileentity.multi

import latibro.automation.core.context.CoreContext
import latibro.automation.core.context.tileentity.TileEntityLinkContext
import latibro.automation.core.context.tileentity.multi.AbstractTileEntityMultiLinkContext
import latibro.automation.nativeimpl.context.server.CoreServerLinkContext
import latibro.automation.nativeimpl.context.server.DefaultCoreServerLinkContext
import latibro.automation.nativeimpl.context.tileentity.CoreTileEntityLinkContext
import latibro.automation.nativeimpl.context.tileentity.InstanceCoreTileEntityLinkContext
import net.minecraft.tileentity.TileEntity

abstract class CoreTileEntityMultiLinkContext extends AbstractTileEntityMultiLinkContext implements CoreContext {

    abstract List<TileEntity> getNativeTileEntityList()

    @Override
    boolean isLinked() {
        return nativeTileEntityList
    }

    @Override
    int count() {
        return nativeTileEntityList.size()
    }

    @Override
    List<TileEntityLinkContext> asList() {
        return wrapNativeTileEntityList(nativeTileEntityList)
    }

    @Override
    List<CoreTileEntityLinkContext> asList(int maxCount) {
        return wrapNativeTileEntityList(nativeTileEntityList.take(maxCount))
    }

    @Override
    CoreServerLinkContext getServer() {
        return new DefaultCoreServerLinkContext()
    }

    protected static CoreTileEntityLinkContext wrapNativeTileEntity(TileEntity nativeTileEntity) {
        return new InstanceCoreTileEntityLinkContext(nativeTileEntity)
    }

    protected static List<CoreTileEntityLinkContext> wrapNativeTileEntityList(List<TileEntity> nativeTileEntityList) {
        return nativeTileEntityList.collect {
            wrapNativeTileEntity(it)
        }
    }

}
