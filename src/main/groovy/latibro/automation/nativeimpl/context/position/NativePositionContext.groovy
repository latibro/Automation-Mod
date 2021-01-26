package latibro.automation.nativeimpl.context.position

import latibro.automation.core.context.position.PositionContext
import latibro.automation.nativeimpl.context.NativeContext
import net.minecraft.util.math.BlockPos

interface NativePositionContext extends PositionContext, NativeContext {

    BlockPos getNativePosition()

}
