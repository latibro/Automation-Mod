package latibro.automation.nativeimpl.context.entity

import groovy.transform.CompileStatic
import latibro.automation.core.context.CoreContext
import latibro.automation.nativeimpl.context.server.NativeServerContext
import net.minecraft.entity.Entity

@CompileStatic
final class NativeStaticUUIDEntityContext extends AbstractNativeEntityContext implements CoreContext {

    private final UUID uuid
    private final NativeServerContext serverContext

    NativeStaticUUIDEntityContext(UUID uuid, NativeServerContext server) {
        this.uuid = Objects.requireNonNull(uuid)
        this.serverContext = Objects.requireNonNull(server)
    }

    Entity getNativeEntity() {
        return serverContext.nativeServer.getEntityFromUuid(uuid)
    }

    @Override
    NativeServerContext getServerContext() {
        return serverContext
    }

    @Override
    UUID getUUID() {
        return uuid
    }

    @Override
    boolean isLoaded() {
        return nativeEntity ? super.isLoaded() : false
    }

}
