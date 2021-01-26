package latibro.automation.nativeimpl.context.entity

import latibro.automation.core.context.entity.EntityContext
import latibro.automation.nativeimpl.context.NativeContext
import net.minecraft.entity.Entity

interface NativeEntityContext extends EntityContext, NativeContext {

    Entity getNativeEntity()

}
