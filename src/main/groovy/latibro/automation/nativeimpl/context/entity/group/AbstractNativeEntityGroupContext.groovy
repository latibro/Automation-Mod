package latibro.automation.nativeimpl.context.entity.group


import latibro.automation.core.context.entity.group.AbstractEntityGroupContext
import latibro.automation.nativeimpl.context.entity.NativeEntityContext
import latibro.automation.nativeimpl.context.entity.NativeStaticInstanceEntityContext
import latibro.automation.nativeimpl.context.server.DefaultNativeServerContext
import latibro.automation.nativeimpl.context.server.NativeServerContext
import net.minecraft.entity.Entity

abstract class AbstractNativeEntityGroupContext extends AbstractEntityGroupContext implements NativeEntityGroupContext {

    @Override
    int size() {
        return nativeEntityCollection.size()
    }

    @Override
    List<NativeEntityContext> getAll() {
        def result = nativeEntityCollection.collect {
            wrapNativeEntity(it)
        }
        return result
    }

    @Override
    NativeEntityContext getAtIndex(int index) {
        return wrapNativeEntity(nativeEntityCollection[index])
    }

    @Override
    NativeServerContext getServerContext() {
        return new DefaultNativeServerContext()
    }

    protected static NativeEntityContext wrapNativeEntity(Entity nativeEntity) {
        return new NativeStaticInstanceEntityContext(nativeEntity)
    }

}
