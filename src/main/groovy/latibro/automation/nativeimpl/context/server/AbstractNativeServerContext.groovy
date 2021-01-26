package latibro.automation.nativeimpl.context.server

import latibro.automation.core.context.entity.collection.EntityCollectionContext
import latibro.automation.nativeimpl.context.entity.collection.NativeServerLoadedEntityCollectionContext
import latibro.automation.core.context.server.AbstractServerContext

abstract class AbstractNativeServerContext extends AbstractServerContext implements NativeServerContext{

    @Override
    EntityCollectionContext getLoadedEntityCollectionContext() {
        return new NativeServerLoadedEntityCollectionContext(this)
    }

}