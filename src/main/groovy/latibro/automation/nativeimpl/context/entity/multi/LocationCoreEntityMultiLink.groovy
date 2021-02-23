package latibro.automation.nativeimpl.context.entity.multi

import com.google.common.base.Predicate
import groovy.transform.CompileStatic
import latibro.automation.core.LinkType
import latibro.automation.nativeimpl.context.location.CoreLocationContext
import net.minecraft.entity.Entity
import net.minecraft.util.math.AxisAlignedBB

import javax.annotation.Nullable

@CompileStatic
final class LocationCoreEntityMultiLink extends CoreEntityMultiLinkContext {

    private final CoreLocationContext location
    private final boolean includeBoundingBoxes

    LocationCoreEntityMultiLink(CoreLocationContext location) {
        this(location, true)
    }

    LocationCoreEntityMultiLink(CoreLocationContext location, boolean includeBoundingBoxes) {
        this.location = Objects.requireNonNull(location)
        this.includeBoundingBoxes = includeBoundingBoxes
    }

    List<Entity> getNativeEntityList() {
        return (List<Entity>) location.world.nativeWorld.getEntities(Entity.class, new Predicate<Entity>() {
            @Override
            boolean apply(@Nullable Entity input) {
                if (includeBoundingBoxes) {
                    return input.getEntityBoundingBox().intersects(new AxisAlignedBB(location.nativeLocation))
                }
                return input.position == location.nativeLocation
            }
        })
    }

    @Override
    LinkType getLinkType() {
        return LinkType.DYNAMIC
    }

}
