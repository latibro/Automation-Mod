package latibro.automation.nativeimpl.context.tileentity.multi


import groovy.transform.CompileStatic
import latibro.automation.AutomationMod
import latibro.automation.core.LinkType
import latibro.automation.nativeimpl.context.entity.CoreEntityLinkContext
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.AxisAlignedBB

@CompileStatic
final class EntityNearbyCoreTileEntityMultiLinkContext extends CoreTileEntityMultiLinkContext {

    private final CoreEntityLinkContext entity
    private final boolean includeBoundingBoxes
    private final double range

    EntityNearbyCoreTileEntityMultiLinkContext(CoreEntityLinkContext entity, double range) {
        this(entity, range, true)
    }

    EntityNearbyCoreTileEntityMultiLinkContext(CoreEntityLinkContext entity, double range, boolean includeBoundingBoxes) {
        this.entity = Objects.requireNonNull(entity)
        this.range = range
        this.includeBoundingBoxes = includeBoundingBoxes
    }

    List<TileEntity> getNativeTileEntityList() {
        def bb = entity.nativeEntity.getEntityBoundingBox()

        if (bb.class.name == "cam72cam.mod.entity.boundingbox.BoundingBox") {
            //TODO This is a workaround for an issue in IR https://github.com/TeamOpenIndustry/ImmersiveRailroading/issues/945
            bb = new AxisAlignedBB(bb.minX, bb.minY, bb.minZ, bb.maxX, bb.maxY, bb.maxZ)
        }

        AutomationMod.logger.debug("bb {}", bb)
        def rangeBB = bb.grow(range)
        def loadedTileEntityList = entity.nativeEntity.world.@loadedTileEntityList.findAll {
            if (includeBoundingBoxes) {
                AutomationMod.logger.debug("block {}", it)
                def thisBB = it.world.getBlockState(it.pos).getBoundingBox(it.world, it.pos).offset(it.pos)
                AutomationMod.logger.debug("thisBB {}", thisBB)
                def intersects = rangeBB.intersects(thisBB)
                return intersects
            }
            def distance = Math.sqrt(entity.nativeEntity.getDistanceSqToCenter(it.pos))
            return distance <= range
        }
        return loadedTileEntityList
    }

    @Override
    LinkType getLinkType() {
        return LinkType.DYNAMIC
    }

}
