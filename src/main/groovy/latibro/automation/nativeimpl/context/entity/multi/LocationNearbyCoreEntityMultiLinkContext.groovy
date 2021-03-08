package latibro.automation.nativeimpl.context.entity.multi


import groovy.transform.CompileStatic
import latibro.automation.core.LinkType
import latibro.automation.nativeimpl.context.location.CoreLocationLinkContext
import net.minecraft.entity.Entity
import net.minecraft.util.math.AxisAlignedBB

@CompileStatic
final class LocationNearbyCoreEntityMultiLinkContext extends CoreEntityMultiLinkContext {

    private final CoreLocationLinkContext location
    private final boolean includeBoundingBoxes
    private final double range

    LocationNearbyCoreEntityMultiLinkContext(CoreLocationLinkContext location, double range) {
        this(location, range, true)
    }

    LocationNearbyCoreEntityMultiLinkContext(CoreLocationLinkContext location, double range, boolean includeBoundingBoxes) {
        this.location = Objects.requireNonNull(location)
        this.range = range
        this.includeBoundingBoxes = includeBoundingBoxes
    }

    List<Entity> getNativeEntityList() {
        def loadedEntityList = location.world.nativeWorld.@loadedEntityList.findAll {
            if (includeBoundingBoxes) {
                return it.getEntityBoundingBox().intersects(new AxisAlignedBB(location.nativeLocation).grow(range))
            }
            return location.nativeLocation.getDistance(it.position.x, it.position.y, it.position.z) <= range
        }
        return loadedEntityList
    }

    @Override
    LinkType getLinkType() {
        return LinkType.DYNAMIC
    }

}
