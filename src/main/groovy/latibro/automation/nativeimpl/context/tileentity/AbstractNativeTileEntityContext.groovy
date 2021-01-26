package latibro.automation.nativeimpl.context.tileentity


import latibro.automation.nativeimpl.context.position.NativeTileEntityPositionContext
import latibro.automation.core.context.position.PositionContext
import latibro.automation.core.context.tileentity.AbstractTileEntityContext
import latibro.automation.nativeimpl.context.world.NativeTileEntityWorldContext
import latibro.automation.core.context.world.WorldContext

abstract class AbstractNativeTileEntityContext extends AbstractTileEntityContext implements NativeTileEntityContext {

    @Override
    WorldContext getWorldContext() {
        return new NativeTileEntityWorldContext(this)
    }

    @Override
    PositionContext getPositionContext() {
        return new NativeTileEntityPositionContext(this)
    }

}
