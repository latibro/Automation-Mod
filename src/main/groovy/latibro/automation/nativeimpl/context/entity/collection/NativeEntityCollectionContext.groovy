package latibro.automation.nativeimpl.context.entity.collection

import latibro.automation.core.context.entity.collection.EntityCollectionContext
import latibro.automation.nativeimpl.context.NativeContext
import latibro.automation.nativeimpl.context.entity.NativeEntityContext
import latibro.automation.nativeimpl.context.server.NativeServerContext
import net.minecraft.entity.Entity

interface NativeEntityCollectionContext extends EntityCollectionContext, NativeContext {

    Collection<Entity> getNativeEntityCollection()

    @Override
    Collection<NativeEntityContext> getAll()

    @Override
    NativeEntityContext getAt(int index)

    @Override
    NativeServerContext getServerContext()

}
