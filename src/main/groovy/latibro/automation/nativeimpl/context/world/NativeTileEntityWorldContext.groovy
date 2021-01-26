package latibro.automation.nativeimpl.context.world

import groovy.transform.CompileStatic
import latibro.automation.core.context.CoreContext
import latibro.automation.nativeimpl.context.server.NativeTileEntityServerContext
import latibro.automation.core.context.server.ServerContext
import latibro.automation.nativeimpl.context.tileentity.NativeTileEntityContext
import net.minecraft.world.World

@CompileStatic
final class NativeTileEntityWorldContext extends AbstractNativeWorldContext implements CoreContext {

    private final NativeTileEntityContext tileEntityContext

    NativeTileEntityWorldContext(NativeTileEntityContext tileEntityContext) {
        this.tileEntityContext = Objects.requireNonNull(tileEntityContext)
    }

    World getNativeWorld() {
        return tileEntityContext.nativeTileEntity.world
    }

    @Override
    ServerContext getServerContext() {
        return new NativeTileEntityServerContext(tileEntityContext)
    }

}