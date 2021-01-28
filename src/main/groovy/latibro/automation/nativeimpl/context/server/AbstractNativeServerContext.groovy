package latibro.automation.nativeimpl.context.server

import latibro.automation.core.context.entity.collection.EntityCollectionContext
import latibro.automation.nativeimpl.context.entity.collection.NativeServerLoadedEntityCollectionContext

abstract class AbstractNativeServerContext implements NativeServerContext {

    @Override
    EntityCollectionContext getLoadedEntityCollectionContext() {
        return new NativeServerLoadedEntityCollectionContext(this)
    }

}