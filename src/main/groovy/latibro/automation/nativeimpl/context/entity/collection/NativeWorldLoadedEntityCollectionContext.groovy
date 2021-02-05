package latibro.automation.nativeimpl.context.entity.collection

import com.google.common.base.Predicate
import groovy.transform.CompileStatic
import latibro.automation.core.context.CoreContext
import latibro.automation.nativeimpl.context.world.NativeWorldContext
import net.minecraft.entity.Entity

import javax.annotation.Nullable

@CompileStatic
final class NativeWorldLoadedEntityCollectionContext extends AbstractNativeEntityCollectionContext implements CoreContext {

    private final NativeWorldContext worldContext

    NativeWorldLoadedEntityCollectionContext(NativeWorldContext worldContext) {
        this.worldContext = Objects.requireNonNull(worldContext)
    }

    Collection<Entity> getNativeEntityCollection() {
        //return worldContext.nativeWorld.@loadedEntityList
        return worldContext.nativeWorld.getEntities(Entity.class, new Predicate<Entity>() {
            @Override
            boolean apply(@Nullable Entity input) {
                return true
            }
        })
    }

}
