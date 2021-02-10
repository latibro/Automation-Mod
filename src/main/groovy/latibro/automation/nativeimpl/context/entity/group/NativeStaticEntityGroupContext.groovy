package latibro.automation.nativeimpl.context.entity.group

import latibro.automation.core.context.CoreContext
import net.minecraft.entity.Entity

final class NativeStaticEntityGroupContext extends AbstractNativeEntityGroupContext implements CoreContext {

    private final Collection<Entity> nativeEntityCollection

    NativeStaticEntityGroupContext(Collection<Entity> nativeEntityCollection) {
        this.nativeEntityCollection = Objects.requireNonNull(nativeEntityCollection)
    }

    Collection<Entity> getNativeEntityCollection() {
        return nativeEntityCollection
    }

}
