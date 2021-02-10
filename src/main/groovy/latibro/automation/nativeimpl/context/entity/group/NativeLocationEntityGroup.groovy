package latibro.automation.nativeimpl.context.entity.group

import groovy.transform.CompileStatic
import latibro.automation.core.context.CoreContext
import latibro.automation.nativeimpl.context.location.NativeLocationContext
import net.minecraft.entity.Entity

@CompileStatic
final class NativeLocationEntityGroup extends AbstractNativeEntityGroupContext implements CoreContext {

    private final NativeLocationContext locationContext

    NativeLocationEntityGroup(NativeLocationContext locationContext) {
        this.locationContext = Objects.requireNonNull(locationContext)
    }

    Collection<Entity> getNativeEntityCollection() {
        locationContext.worldContext.nativeWorld.loadedEntityList.findAll {
            it.position == locationContext.nativeLocation
        }
    }

}
