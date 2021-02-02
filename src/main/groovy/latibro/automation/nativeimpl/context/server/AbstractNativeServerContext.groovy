package latibro.automation.nativeimpl.context.server


import latibro.automation.nativeimpl.context.entity.collection.NativeEntityCollectionContext
import latibro.automation.nativeimpl.context.entity.collection.NativeServerLoadedEntityCollectionContext

abstract class AbstractNativeServerContext implements NativeServerContext {

    @Override
    NativeEntityCollectionContext getLoadedEntityCollectionContext() {
        return new NativeServerLoadedEntityCollectionContext(this)
    }

}