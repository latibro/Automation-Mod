package latibro.automation.nativeimpl.context.location

import groovy.transform.CompileStatic
import latibro.automation.nativeimpl.context.entity.group.NativeEntityGroupContext
import latibro.automation.nativeimpl.context.entity.group.NativeLocationEntityGroup

@CompileStatic
abstract class AbstractNativeLocationContext implements NativeLocationContext {

    @Override
    boolean isLoaded() {
        return worldContext.nativeWorld.isBlockLoaded(nativeLocation)
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
    NativeEntityGroupContext getEntities() {
        return new NativeLocationEntityGroup(this)
    }

    @Override
    NativeEntityGroupContext getEntities(boolean includeBoundingBoxes) {
        return new NativeLocationEntityGroup(this, includeBoundingBoxes)
    }

    @Override
    double getDistanceToCoordinates(int x, int y, int z) {
        return nativeLocation.getDistance(x, y, z)
    }

}
