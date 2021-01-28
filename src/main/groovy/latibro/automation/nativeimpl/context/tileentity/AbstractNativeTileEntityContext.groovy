package latibro.automation.nativeimpl.context.tileentity

import latibro.automation.core.context.position.PositionContext
import latibro.automation.core.context.world.WorldContext
import latibro.automation.nativeimpl.context.position.NativeTileEntityPositionContext
import latibro.automation.nativeimpl.context.world.NativeTileEntityWorldContext

abstract class AbstractNativeTileEntityContext implements NativeTileEntityContext {

    @Override
    WorldContext getWorldContext() {
        return new NativeTileEntityWorldContext(this)
    }

    @Override
    PositionContext getPositionContext() {
        return new NativeTileEntityPositionContext(this)
    }

}
