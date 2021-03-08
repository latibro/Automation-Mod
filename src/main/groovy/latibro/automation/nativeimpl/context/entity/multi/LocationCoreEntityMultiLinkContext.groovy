package latibro.automation.nativeimpl.context.entity.multi


import groovy.transform.CompileStatic
import latibro.automation.core.LinkType
import latibro.automation.nativeimpl.context.location.CoreLocationLinkContext
import net.minecraft.entity.Entity
import net.minecraft.util.math.AxisAlignedBB

@CompileStatic
final class LocationCoreEntityMultiLinkContext extends CoreEntityMultiLinkContext {

    private final CoreLocationLinkContext location
    private final boolean includeBoundingBoxes

    LocationCoreEntityMultiLinkContext(CoreLocationLinkContext location) {
        this(location, true)
    }

    LocationCoreEntityMultiLinkContext(CoreLocationLinkContext location, boolean includeBoundingBoxes) {
        this.location = Objects.requireNonNull(location)
        this.includeBoundingBoxes = includeBoundingBoxes
    }

    List<Entity> getNativeEntityList() {
        def loadedEntityList = location.world.nativeWorld.@loadedEntityList.findAll {
            if (includeBoundingBoxes) {
                return it.getEntityBoundingBox().intersects(new AxisAlignedBB(location.nativeLocation))
            }
            return it.position == location.nativeLocation
        }
        return loadedEntityList
    }

    @Override
    LinkType getLinkType() {
        return LinkType.DYNAMIC
    }

}
