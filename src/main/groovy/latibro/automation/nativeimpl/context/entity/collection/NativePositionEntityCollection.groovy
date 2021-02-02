package latibro.automation.nativeimpl.context.entity.collection

import latibro.automation.core.context.CoreContext
import latibro.automation.nativeimpl.context.position.NativePositionContext
import net.minecraft.entity.Entity

final class NativePositionEntityCollection extends AbstractNativeEntityCollectionContext implements CoreContext {

    private final NativePositionContext positionContext

    NativePositionEntityCollection(NativePositionContext positionContext) {
        this.positionContext = Objects.requireNonNull(positionContext)
    }

    Collection<Entity> getNativeEntityCollection() {
        positionContext.worldContext.nativeWorld.loadedEntityList.findAll {
            it.position == positionContext.nativePosition
        }
    }

}
