package latibro.automation.nativeimpl.context.server

import groovy.transform.CompileStatic
import latibro.automation.core.context.CoreContext
import latibro.automation.nativeimpl.context.entity.NativeEntityContext
import net.minecraft.server.MinecraftServer

@CompileStatic
final class NativeEntityServerContext extends AbstractNativeServerContext implements CoreContext {

    private final NativeEntityContext entityContext

    NativeEntityServerContext(NativeEntityContext entityContext) {
        this.entityContext = Objects.requireNonNull(entityContext)
    }

    MinecraftServer getNativeServer() {
        return entityContext.nativeEntity.server
    }

}