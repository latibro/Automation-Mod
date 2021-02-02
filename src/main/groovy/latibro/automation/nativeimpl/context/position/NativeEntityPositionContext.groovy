package latibro.automation.nativeimpl.context.position

import groovy.transform.CompileStatic
import latibro.automation.core.context.CoreContext
import latibro.automation.nativeimpl.context.entity.NativeEntityContext
import latibro.automation.nativeimpl.context.world.NativeWorldContext
import net.minecraft.util.math.BlockPos

@CompileStatic
final class NativeEntityPositionContext extends AbstractNativePositionContext implements CoreContext {

    private final NativeEntityContext entityContext

    NativeEntityPositionContext(NativeEntityContext entityContext) {
        this.entityContext = Objects.requireNonNull(entityContext)
    }

    @Override
    BlockPos getNativePosition() {
        return entityContext.nativeEntity.position
    }

    @Override
    NativeWorldContext getWorldContext() {
        return entityContext.worldContext
    }

}
