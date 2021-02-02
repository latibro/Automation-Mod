package latibro.automation.nativeimpl.context.tileentity


import latibro.automation.nativeimpl.context.location.NativeLocationContext
import latibro.automation.nativeimpl.context.location.NativeTileEntityLocationContext
import latibro.automation.nativeimpl.context.world.NativeTileEntityWorldContext
import latibro.automation.nativeimpl.context.world.NativeWorldContext

abstract class AbstractNativeTileEntityContext implements NativeTileEntityContext {

    @Override
    NativeWorldContext getWorldContext() {
        return new NativeTileEntityWorldContext(this)
    }

    @Override
    NativeLocationContext getLocationContext() {
        return new NativeTileEntityLocationContext(this)
    }

}
