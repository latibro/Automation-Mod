package latibro.automation.nativeimpl.context.world

import latibro.automation.core.context.world.WorldContext
import latibro.automation.nativeimpl.context.NativeContext
import net.minecraft.world.World

interface NativeWorldContext extends WorldContext, NativeContext {

    World getNativeWorld()

}