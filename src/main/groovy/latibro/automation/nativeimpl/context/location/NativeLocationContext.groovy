package latibro.automation.nativeimpl.context.location

import latibro.automation.core.context.location.LocationContext
import latibro.automation.nativeimpl.context.NativeContext
import latibro.automation.nativeimpl.context.entity.collection.NativeEntityCollectionContext
import latibro.automation.nativeimpl.context.world.NativeWorldContext
import net.minecraft.util.math.BlockPos

interface NativeLocationContext extends LocationContext, NativeContext {

    BlockPos getNativeLocation()

    @Override
    NativeEntityCollectionContext getEntityCollectionContext()

    @Override
    NativeWorldContext getWorldContext()

}
