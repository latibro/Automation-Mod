package latibro.automation.nativeimpl.context.world

import latibro.automation.core.context.entity.collection.EntityCollectionContext
import latibro.automation.nativeimpl.context.entity.collection.NativeWorldLoadedEntityCollectionContext
import latibro.automation.core.context.world.AbstractWorldContext
import net.minecraft.world.World

abstract class AbstractNativeWorldContext extends AbstractWorldContext implements NativeWorldContext {

    abstract World getNativeWorld()

    @Override
    EntityCollectionContext getLoadedEntityCollectionContext() {
        return new NativeWorldLoadedEntityCollectionContext(this)
    }

}