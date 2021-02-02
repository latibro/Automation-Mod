package latibro.automation.nativeimpl.context.location

import groovy.transform.CompileStatic
import latibro.automation.core.context.CoreContext
import latibro.automation.nativeimpl.context.entity.NativeEntityContext
import latibro.automation.nativeimpl.context.world.NativeWorldContext
import net.minecraft.util.math.BlockPos

@CompileStatic
final class NativeEntityLocationContext extends AbstractNativeLocationContext implements CoreContext {

    private final NativeEntityContext entityContext

    NativeEntityLocationContext(NativeEntityContext entityContext) {
        this.entityContext = Objects.requireNonNull(entityContext)
    }

    @Override
    BlockPos getNativeLocation() {
        return entityContext.nativeEntity.position
    }

    @Override
    NativeWorldContext getWorldContext() {
        return entityContext.worldContext
    }

}
