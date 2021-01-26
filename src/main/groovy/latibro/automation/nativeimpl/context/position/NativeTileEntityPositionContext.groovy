package latibro.automation.nativeimpl.context.position

import groovy.transform.CompileStatic
import latibro.automation.core.context.CoreContext
import latibro.automation.nativeimpl.context.tileentity.AbstractNativeTileEntityContext
import latibro.automation.core.context.world.WorldContext
import net.minecraft.util.math.BlockPos

@CompileStatic
final class NativeTileEntityPositionContext extends AbstractNativePositionContext implements CoreContext {

    private final AbstractNativeTileEntityContext tileEntityContext

    NativeTileEntityPositionContext(AbstractNativeTileEntityContext tileEntityContext) {
        this.tileEntityContext = Objects.requireNonNull(tileEntityContext)
    }

    @Override
    BlockPos getNativePosition() {
        return tileEntityContext.nativeTileEntity.pos
    }

    @Override
    WorldContext getWorldContext() {
        return tileEntityContext.worldContext
    }

}
