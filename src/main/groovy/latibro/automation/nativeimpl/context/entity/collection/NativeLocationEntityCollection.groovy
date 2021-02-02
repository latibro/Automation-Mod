package latibro.automation.nativeimpl.context.entity.collection

import latibro.automation.core.context.CoreContext
import latibro.automation.nativeimpl.context.location.NativeLocationContext
import net.minecraft.entity.Entity

final class NativeLocationEntityCollection extends AbstractNativeEntityCollectionContext implements CoreContext {

    private final NativeLocationContext locationContext

    NativeLocationEntityCollection(NativeLocationContext locationContext) {
        this.locationContext = Objects.requireNonNull(locationContext)
    }

    Collection<Entity> getNativeEntityCollection() {
        locationContext.worldContext.nativeWorld.loadedEntityList.findAll {
            it.position == locationContext.nativeLocation
        }
    }

}
