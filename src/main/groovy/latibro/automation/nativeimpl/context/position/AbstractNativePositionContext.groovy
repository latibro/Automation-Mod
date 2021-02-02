package latibro.automation.nativeimpl.context.position

import groovy.transform.CompileStatic
import latibro.automation.nativeimpl.context.entity.collection.NativeEntityCollectionContext
import latibro.automation.nativeimpl.context.entity.collection.NativePositionEntityCollection

@CompileStatic
abstract class AbstractNativePositionContext implements NativePositionContext {

    @Override
    boolean isLoaded() {
        return worldContext.nativeWorld.isBlockLoaded(nativePosition)
    }

    @Override
    int getX() {
        return nativePosition.x
    }

    @Override
    int getY() {
        return nativePosition.x
    }

    @Override
    int getZ() {
        return nativePosition.x
    }

    @Override
    NativeEntityCollectionContext getEntityCollectionContext() {
        return new NativePositionEntityCollection(this)
    }

}
