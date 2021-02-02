package latibro.automation.nativeimpl.context.tileentity


import latibro.automation.nativeimpl.context.position.NativePositionContext
import latibro.automation.nativeimpl.context.position.NativeTileEntityPositionContext
import latibro.automation.nativeimpl.context.world.NativeTileEntityWorldContext
import latibro.automation.nativeimpl.context.world.NativeWorldContext

abstract class AbstractNativeTileEntityContext implements NativeTileEntityContext {

    @Override
    NativeWorldContext getWorldContext() {
        return new NativeTileEntityWorldContext(this)
    }

    @Override
    NativePositionContext getPositionContext() {
        return new NativeTileEntityPositionContext(this)
    }

}
