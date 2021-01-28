package latibro.automation.nativeimpl.context.entity.collection

import latibro.automation.core.context.entity.EntityContext
import latibro.automation.core.context.server.ServerContext
import latibro.automation.nativeimpl.context.entity.StaticNativeEntityContext
import latibro.automation.nativeimpl.context.server.DefaultNativeServerContext

abstract class AbstractNativeEntityCollectionContext implements NativeEntityCollectionContext {

    @Override
    int size() {
        return nativeEntityCollection.size()
    }

    @Override
    Collection<EntityContext> getAll() {
        return nativeEntityCollection.collect {
            new StaticNativeEntityContext(it)
        }
    }

    @Override
    EntityContext getAt(int index) {
        def nativeObject = nativeEntityCollection.getAt(index)
        return new StaticNativeEntityContext(nativeObject)
    }

    @Override
    ServerContext getServerContext() {
        return new DefaultNativeServerContext()
    }

}
