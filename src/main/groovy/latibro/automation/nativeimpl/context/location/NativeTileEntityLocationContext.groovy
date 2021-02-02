package latibro.automation.nativeimpl.context.location

import groovy.transform.CompileStatic
import latibro.automation.core.context.CoreContext
import latibro.automation.nativeimpl.context.tileentity.AbstractNativeTileEntityContext
import latibro.automation.nativeimpl.context.tileentity.NativeTileEntityContext
import latibro.automation.nativeimpl.context.world.NativeWorldContext
import net.minecraft.util.math.BlockPos

@CompileStatic
final class NativeTileEntityLocationContext extends AbstractNativeLocationContext implements CoreContext {

    private final NativeTileEntityContext tileEntityContext

    NativeTileEntityLocationContext(AbstractNativeTileEntityContext tileEntityContext) {
        this.tileEntityContext = Objects.requireNonNull(tileEntityContext)
    }

    @Override
    BlockPos getNativeLocation() {
        return tileEntityContext.nativeTileEntity.pos
    }

    @Override
    NativeWorldContext getWorldContext() {
        return tileEntityContext.worldContext
    }

}
