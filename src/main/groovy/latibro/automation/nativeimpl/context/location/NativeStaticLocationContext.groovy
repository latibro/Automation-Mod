package latibro.automation.nativeimpl.context.location

import groovy.transform.CompileStatic
import latibro.automation.core.context.CoreContext
import latibro.automation.nativeimpl.context.world.NativeWorldContext
import net.minecraft.util.math.BlockPos

@CompileStatic
final class NativeStaticLocationContext extends AbstractNativeLocationContext implements CoreContext {

    private final BlockPos nativeLocation
    private final NativeWorldContext worldContext

    NativeStaticLocationContext(BlockPos location, NativeWorldContext world) {
        nativeLocation = Objects.requireNonNull(location)
        this.worldContext = Objects.requireNonNull(world)
    }

    NativeStaticLocationContext(int x, int y, int z, NativeWorldContext world) {
        this(new BlockPos(Objects.requireNonNull(x), Objects.requireNonNull(y) , Objects.requireNonNull(z)), world)
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
