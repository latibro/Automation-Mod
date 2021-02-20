package latibro.automation.nativeimpl.context.entity.group

import groovy.transform.CompileStatic
import latibro.automation.core.context.CoreContext
import latibro.automation.nativeimpl.context.location.NativeLocationContext
import net.minecraft.entity.Entity
import net.minecraft.util.math.AxisAlignedBB

@CompileStatic
final class NativeLocationEntityGroup extends AbstractNativeEntityGroupContext implements CoreContext {

    private final NativeLocationContext locationContext
    private final boolean includeBoundingBoxes

    NativeLocationEntityGroup(NativeLocationContext locationContext) {
        this(locationContext, true)
    }

    NativeLocationEntityGroup(NativeLocationContext locationContext, boolean includeBoundingBoxes) {
        this.locationContext = Objects.requireNonNull(locationContext)
        this.includeBoundingBoxes = includeBoundingBoxes
    }

    Collection<Entity> getNativeEntityCollection() {
        locationContext.worldContext.nativeWorld.loadedEntityList.findAll {
            if (includeBoundingBoxes) {
                return it.getEntityBoundingBox().intersects(new AxisAlignedBB(locationContext.nativeLocation))
            }
            return it.position == locationContext.nativeLocation
        }
    }

}
