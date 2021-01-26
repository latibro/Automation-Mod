package latibro.automation.nativeimpl.context.entity.collection

import latibro.automation.core.context.CoreContext
import latibro.automation.nativeimpl.context.server.NativeServerContext
import net.minecraft.entity.Entity

final class NativeServerLoadedEntityCollectionContext extends AbstractNativeEntityCollectionContext implements CoreContext {

    private final NativeServerContext serverContext

    NativeServerLoadedEntityCollectionContext(NativeServerContext serverContext) {
        this.serverContext = Objects.requireNonNull(serverContext)
    }

    Collection<Entity> getNativeEntityCollection() {
        def entities = []
        serverContext.nativeServer.worlds.findResults { it }.each { entities.addAll(it.loadedEntityList) }
        return entities
    }

}
