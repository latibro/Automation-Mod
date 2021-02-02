package latibro.automation.nativeimpl.context.position

import latibro.automation.core.context.position.PositionContext
import latibro.automation.nativeimpl.context.NativeContext
import latibro.automation.nativeimpl.context.entity.collection.NativeEntityCollectionContext
import latibro.automation.nativeimpl.context.world.NativeWorldContext
import net.minecraft.util.math.BlockPos

interface NativePositionContext extends PositionContext, NativeContext {

    BlockPos getNativePosition()

    @Override
    NativeEntityCollectionContext getEntityCollectionContext()

    @Override
    NativeWorldContext getWorldContext()

}
