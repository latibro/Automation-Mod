package latibro.automation.nativeimpl.context.entity.collection

import latibro.automation.core.context.CoreContext
import latibro.automation.nativeimpl.context.world.NativeWorldContext
import net.minecraft.entity.Entity

final class NativeWorldLoadedEntityCollectionContext extends AbstractNativeEntityCollectionContext implements CoreContext {

    private final NativeWorldContext worldContext

    NativeWorldLoadedEntityCollectionContext(NativeWorldContext worldContext) {
        this.worldContext = Objects.requireNonNull(worldContext)
    }

    Collection<Entity> getNativeEntityCollection() {
        return worldContext.nativeWorld.loadedEntityList
    }

}
