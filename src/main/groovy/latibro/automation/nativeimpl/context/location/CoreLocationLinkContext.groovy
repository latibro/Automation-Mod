package latibro.automation.nativeimpl.context.location

import groovy.transform.CompileStatic
import latibro.automation.core.context.CoreContext
import latibro.automation.core.context.location.LocationContext
import latibro.automation.nativeimpl.context.entity.multi.CoreEntityMultiLinkContext
import latibro.automation.nativeimpl.context.entity.multi.LocationCoreEntityMultiLink
import latibro.automation.nativeimpl.context.world.CoreWorldLinkContext
import net.minecraft.util.math.BlockPos

@CompileStatic
abstract class CoreLocationLinkContext implements LocationContext, CoreContext {

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
    CoreEntityMultiLinkContext getEntities() {
        return new LocationCoreEntityMultiLink(this)
    }

    @Override
    CoreEntityMultiLinkContext getEntities(boolean includeBoundingBoxes) {
        return new LocationCoreEntityMultiLink(this, includeBoundingBoxes)
    }

    @Override
    double getDistanceToCoordinates(int x, int y, int z) {
        return nativeLocation.getDistance(x, y, z)
    }

}
