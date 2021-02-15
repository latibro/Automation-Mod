package latibro.automation.nativeimpl.context.entity.group

import groovy.transform.CompileStatic
import latibro.automation.core.context.CoreContext
import latibro.automation.nativeimpl.context.location.NativeLocationContext
import net.minecraft.entity.Entity
import net.minecraft.util.math.Vec3d

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

    Collection<Entity> getNativeEntityCollection2() {
        locationContext.worldContext.nativeWorld.loadedEntityList.findAll {
            it.getCollisionBoundingBox().contains(new Vec3d(locationContext.nativeLocation))
        }
    }

}
