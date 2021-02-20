package latibro.automation.nativeimpl.context.location

import latibro.automation.core.context.location.LocationContext
import latibro.automation.nativeimpl.context.NativeContext
import latibro.automation.nativeimpl.context.entity.group.NativeEntityGroupContext
import latibro.automation.nativeimpl.context.world.NativeWorldContext
import net.minecraft.util.math.BlockPos

interface NativeLocationContext extends LocationContext, NativeContext {

    BlockPos getNativeLocation()

    @Override
    NativeEntityGroupContext getEntities()

    @Override
    NativeEntityGroupContext getEntities(boolean includeBoundingBoxes)

    @Override
    NativeWorldContext getWorldContext()

}
