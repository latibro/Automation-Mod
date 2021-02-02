package latibro.automation.nativeimpl.context.location

import groovy.transform.CompileStatic
import latibro.automation.core.context.CoreContext
import latibro.automation.nativeimpl.context.world.NativeWorldContext
import net.minecraft.util.math.BlockPos

@CompileStatic
final class NativeStaticLocationContext extends AbstractNativeLocationContext implements CoreContext {

    private final BlockPos nativeLocation
    private final NativeWorldContext worldContext

    NativeStaticLocationContext(int x, int y, int z, NativeWorldContext world) {
        nativeLocation = new BlockPos(x, y , z)
        this.worldContext = Objects.requireNonNull(world)
    }

    @Override
    BlockPos getNativeLocation() {
        return nativeLocation
    }

    @Override
    NativeWorldContext getWorldContext() {
        return worldContext
    }

}
