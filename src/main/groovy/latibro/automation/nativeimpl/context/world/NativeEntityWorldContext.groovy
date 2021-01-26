package latibro.automation.nativeimpl.context.world

import groovy.transform.CompileStatic
import latibro.automation.core.context.CoreContext
import latibro.automation.nativeimpl.context.entity.NativeEntityContext
import latibro.automation.core.context.server.ServerContext
import net.minecraft.world.World

@CompileStatic
final class NativeEntityWorldContext extends AbstractNativeWorldContext implements CoreContext {

    private final NativeEntityContext entityContext

    NativeEntityWorldContext(NativeEntityContext entityContext) {
        this.entityContext = Objects.requireNonNull(entityContext)
    }

    World getNativeWorld() {
        return entityContext.nativeEntity.world
    }

    @Override
    ServerContext getServerContext() {
        return entityContext.serverContext
    }

}