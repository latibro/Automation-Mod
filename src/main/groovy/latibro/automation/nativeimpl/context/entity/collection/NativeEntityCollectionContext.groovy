package latibro.automation.nativeimpl.context.entity.collection

import latibro.automation.core.context.entity.collection.EntityCollectionContext
import latibro.automation.nativeimpl.context.NativeContext
import net.minecraft.entity.Entity

interface NativeEntityCollectionContext extends EntityCollectionContext, NativeContext {

    Collection<Entity> getNativeEntityCollection()

}
