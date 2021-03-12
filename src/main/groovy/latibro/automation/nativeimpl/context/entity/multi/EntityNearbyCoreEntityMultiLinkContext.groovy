package latibro.automation.nativeimpl.context.entity.multi

import groovy.transform.CompileStatic
import latibro.automation.core.LinkType
import latibro.automation.nativeimpl.context.entity.CoreEntityLinkContext
import net.minecraft.entity.Entity
import net.minecraft.util.math.AxisAlignedBB

@CompileStatic
final class EntityNearbyCoreEntityMultiLinkContext extends CoreEntityMultiLinkContext {

    private final CoreEntityLinkContext entity
    private final boolean includeBoundingBoxes
    private final double range

    EntityNearbyCoreEntityMultiLinkContext(CoreEntityLinkContext entity, double range) {
        this(entity, range, true)
    }

    EntityNearbyCoreEntityMultiLinkContext(CoreEntityLinkContext entity, double range, boolean includeBoundingBoxes) {
        this.entity = Objects.requireNonNull(entity)
        this.range = range
        this.includeBoundingBoxes = includeBoundingBoxes
    }

    List<Entity> getNativeEntityList() {
        def bb = entity.nativeEntity.getEntityBoundingBox()

        if (bb.class.name == "cam72cam.mod.entity.boundingbox.BoundingBox") {
            //TODO This is a workaround for an issue in IR https://github.com/TeamOpenIndustry/ImmersiveRailroading/issues/945
            bb = new AxisAlignedBB(bb.minX, bb.minY, bb.minZ, bb.maxX, bb.maxY, bb.maxZ)
        }

        def rangeAABB = bb.grow(range)
        def loadedEntityList = entity.nativeEntity.world.@loadedEntityList.findAll {
            if (includeBoundingBoxes) {
                def thisBB = it.getEntityBoundingBox()
                def intersects = rangeAABB.intersects(thisBB)
                return intersects
            }
            def distance = Math.sqrt(entity.nativeEntity.getDistanceSq(it))
            return distance <= range
        }
        return loadedEntityList
    }

    @Override
    LinkType getLinkType() {
        return LinkType.DYNAMIC
    }

}
