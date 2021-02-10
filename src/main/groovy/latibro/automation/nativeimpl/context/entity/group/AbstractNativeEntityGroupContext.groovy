package latibro.automation.nativeimpl.context.entity.group

import latibro.automation.core.context.entity.EntityContext
import latibro.automation.core.context.entity.group.AbstractEntityGroupContext
import latibro.automation.nativeimpl.context.entity.NativeEntityContext
import latibro.automation.nativeimpl.context.entity.NativeStaticInstanceEntityContext
import latibro.automation.nativeimpl.context.server.DefaultNativeServerContext
import latibro.automation.nativeimpl.context.server.NativeServerContext

abstract class AbstractNativeEntityGroupContext extends AbstractEntityGroupContext implements NativeEntityGroupContext {

    @Override
    int size() {
        return nativeEntityCollection.size()
    }

    @Override
    List<EntityContext> getAll() {
        return nativeEntityCollection.collect {
            new NativeStaticInstanceEntityContext(it)
        }
    }

    @Override
    NativeEntityContext getAtIndex(int index) {
        def nativeObject = nativeEntityCollection[index]
        return new NativeStaticInstanceEntityContext(nativeObject)
    }

    @Override
    NativeServerContext getServerContext() {
        return new DefaultNativeServerContext()
    }

}
