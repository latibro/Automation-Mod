package latibro.automation.nativeimpl.context.entity.group

import latibro.automation.core.context.entity.group.EntityGroupContext
import latibro.automation.nativeimpl.context.NativeContext
import latibro.automation.nativeimpl.context.entity.NativeEntityContext
import latibro.automation.nativeimpl.context.server.NativeServerContext
import net.minecraft.entity.Entity

interface NativeEntityGroupContext extends EntityGroupContext, NativeContext {

    Collection<Entity> getNativeEntityCollection()

    @Override
    List<NativeEntityContext> getAll()

    @Override
    NativeEntityContext getAtIndex(int index)

    @Override
    NativeServerContext getServerContext()

}
