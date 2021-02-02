package latibro.automation.nativeimpl.context.location

import groovy.transform.CompileStatic
import latibro.automation.nativeimpl.context.entity.collection.NativeEntityCollectionContext
import latibro.automation.nativeimpl.context.entity.collection.NativeLocationEntityCollection

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
    NativeEntityCollectionContext getEntityCollectionContext() {
        return new NativeLocationEntityCollection(this)
    }

}
