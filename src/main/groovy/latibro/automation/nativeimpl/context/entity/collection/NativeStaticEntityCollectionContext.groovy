package latibro.automation.nativeimpl.context.entity.collection

import latibro.automation.core.context.CoreContext
import net.minecraft.entity.Entity

final class NativeStaticEntityCollectionContext extends AbstractNativeEntityCollectionContext implements CoreContext {

    private final Collection<Entity> nativeEntityCollection

    NativeStaticEntityCollectionContext(Collection<Entity> nativeEntityCollection) {
        this.nativeEntityCollection = Objects.requireNonNull(nativeEntityCollection)
    }

    Collection<Entity> getNativeEntityCollection() {
        return nativeEntityCollection
    }

}
