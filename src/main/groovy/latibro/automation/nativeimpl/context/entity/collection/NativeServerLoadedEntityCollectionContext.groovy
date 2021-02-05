package latibro.automation.nativeimpl.context.entity.collection

import com.google.common.base.Predicate
import groovy.transform.CompileStatic
import latibro.automation.core.context.CoreContext
import latibro.automation.nativeimpl.context.server.NativeServerContext
import net.minecraft.entity.Entity

import javax.annotation.Nullable

@CompileStatic
final class NativeServerLoadedEntityCollectionContext extends AbstractNativeEntityCollectionContext implements CoreContext {

    private final NativeServerContext serverContext

    NativeServerLoadedEntityCollectionContext(NativeServerContext serverContext) {
        this.serverContext = Objects.requireNonNull(serverContext)
    }

    Collection<Entity> getNativeEntityCollection() {
        //return (Collection<Entity>) serverContext.nativeServer.worlds.findResults { it }.collect { it.@loadedEntityList }.flatten()
        return (Collection<Entity>) serverContext.nativeServer.worlds.findResults { it }.collect { it.getEntities(Entity.class, new Predicate<Entity>() {
            @Override
            boolean apply(@Nullable Entity input) {
                return true
            }
        }) }.flatten()
    }

}
