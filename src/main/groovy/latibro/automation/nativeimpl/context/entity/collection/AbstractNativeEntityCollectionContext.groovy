package latibro.automation.nativeimpl.context.entity.collection

import latibro.automation.core.context.entity.EntityContext
import latibro.automation.core.context.entity.collection.AbstractEntityCollectionContext
import latibro.automation.nativeimpl.context.entity.NativeEntityContext
import latibro.automation.nativeimpl.context.entity.NativeStaticEntityContext
import latibro.automation.nativeimpl.context.server.DefaultNativeServerContext
import latibro.automation.nativeimpl.context.server.NativeServerContext

abstract class AbstractNativeEntityCollectionContext extends AbstractEntityCollectionContext implements NativeEntityCollectionContext {

    @Override
    int size() {
        return nativeEntityCollection.size()
    }

    @Override
    Collection<EntityContext> getAll() {
        return nativeEntityCollection.collect {
            new NativeStaticEntityContext(it)
        }
    }

    @Override
    NativeEntityContext getAt(int index) {
        def nativeObject = nativeEntityCollection.getAt(index)
        return new NativeStaticEntityContext(nativeObject)
    }

    @Override
    NativeServerContext getServerContext() {
        return new DefaultNativeServerContext()
    }

}
