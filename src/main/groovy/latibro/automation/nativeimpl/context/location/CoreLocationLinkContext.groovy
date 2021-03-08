package latibro.automation.nativeimpl.context.location

import groovy.transform.CompileStatic
import latibro.automation.core.context.CoreContext
import latibro.automation.core.context.location.LocationLinkContext
import latibro.automation.nativeimpl.context.chunk.CoreChunkLinkContext
import latibro.automation.nativeimpl.context.chunk.LocationCoreChunkLinkContext
import latibro.automation.nativeimpl.context.entity.multi.CoreEntityMultiLinkContext
import latibro.automation.nativeimpl.context.entity.multi.LocationCoreEntityMultiLinkContext
import latibro.automation.nativeimpl.context.entity.multi.LocationNearbyCoreEntityMultiLinkContext
import latibro.automation.nativeimpl.context.tileentity.multi.CoreTileEntityMultiLinkContext
import latibro.automation.nativeimpl.context.tileentity.multi.LocationCoreTileEntityMultiLinkContext
import latibro.automation.nativeimpl.context.tileentity.multi.LocationNearbyCoreTileEntityMultiLinkContext
import latibro.automation.nativeimpl.context.world.CoreWorldLinkContext
import net.minecraft.util.math.BlockPos

@CompileStatic
abstract class CoreLocationLinkContext implements LocationLinkContext, CoreContext {

    abstract BlockPos getNativeLocation()

    @Override
    boolean isLinked() {
        return nativeLocation
    }

    @Override
    boolean isLoaded() {
        return world.nativeWorld.isBlockLoaded(nativeLocation)
    }

    @Override
    int getX() {
        return nativeLocation.x
    }

    @Override
    int getY() {
        return nativeLocation.y
    }

    @Override
    int getZ() {
        return nativeLocation.z
    }

    @Override
    abstract CoreWorldLinkContext getWorld()

    @Override
    CoreChunkLinkContext getChunk() {
        return new LocationCoreChunkLinkContext(this)
    }

    @Override
    CoreEntityMultiLinkContext getEntities() {
        return new LocationCoreEntityMultiLinkContext(this)
    }

    @Override
    CoreEntityMultiLinkContext getEntities(boolean includeBoundingBoxes) {
        return new LocationCoreEntityMultiLinkContext(this, includeBoundingBoxes)
    }

    @Override
    CoreEntityMultiLinkContext getNearbyEntities(double range) {
        return new LocationNearbyCoreEntityMultiLinkContext(this, range)
    }

    @Override
    CoreEntityMultiLinkContext getNearbyEntities(double range, boolean includeBoundingBoxes) {
        return new LocationNearbyCoreEntityMultiLinkContext(this, range, includeBoundingBoxes)
    }

    @Override
    CoreTileEntityMultiLinkContext getTileEntities() {
        return new LocationCoreTileEntityMultiLinkContext(this)
    }

    @Override
    CoreTileEntityMultiLinkContext getNearbyTileEntities(double range) {
        return new LocationNearbyCoreTileEntityMultiLinkContext(this, range)
    }

    @Override
    double getDistanceToCoordinates(int x, int y, int z) {
        return nativeLocation.getDistance(x, y, z)
    }

}
