package latibro.automation.nativeimpl.context.entity.group

import com.google.common.base.Predicate
import groovy.transform.CompileStatic
import latibro.automation.core.context.CoreContext
import latibro.automation.nativeimpl.context.location.NativeLocationContext
import net.minecraft.entity.Entity
import net.minecraft.util.math.AxisAlignedBB

import javax.annotation.Nullable

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
        return (Collection<Entity>) locationContext.worldContext.nativeWorld.getEntities(Entity.class, new Predicate<Entity>() {
            @Override
            boolean apply(@Nullable Entity input) {
                if (includeBoundingBoxes) {
                    return input.getEntityBoundingBox().intersects(new AxisAlignedBB(locationContext.nativeLocation))
                }
                return input.position == locationContext.nativeLocation
            }
        })
    }

}
