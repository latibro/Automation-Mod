package latibro.automation.nativeimpl.context.entity

import latibro.automation.core.context.entity.EntityContext
import latibro.automation.nativeimpl.context.NativeContext
import latibro.automation.nativeimpl.context.location.NativeLocationContext
import latibro.automation.nativeimpl.context.server.NativeServerContext
import latibro.automation.nativeimpl.context.world.NativeWorldContext
import net.minecraft.entity.Entity

interface NativeEntityContext extends EntityContext, NativeContext {

    Entity getNativeEntity()

    @Override
    NativeLocationContext getLocationContext()

    @Override
    NativeServerContext getServerContext()

    @Override
    NativeWorldContext getWorldContext()

}
