package latibro.automation.nativeimpl.context.position

import groovy.transform.CompileStatic
import latibro.automation.core.context.CoreContext
import latibro.automation.nativeimpl.context.world.NativeWorldContext
import net.minecraft.util.math.BlockPos

@CompileStatic
final class NativeStaticPositionContext extends AbstractNativePositionContext implements CoreContext {

    private final BlockPos nativePosition
    private final NativeWorldContext worldContext

    NativeStaticPositionContext(int x, int y, int z, NativeWorldContext world) {
        nativePosition = new BlockPos(x, y , z)
        this.worldContext = Objects.requireNonNull(world)
    }

    @Override
    BlockPos getNativePosition() {
        return nativePosition
    }

    @Override
    NativeWorldContext getWorldContext() {
        return worldContext
    }

}
