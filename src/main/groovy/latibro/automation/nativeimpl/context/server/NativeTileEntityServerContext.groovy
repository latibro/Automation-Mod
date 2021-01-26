package latibro.automation.nativeimpl.context.server

import groovy.transform.CompileStatic
import latibro.automation.core.context.CoreContext
import latibro.automation.nativeimpl.context.tileentity.NativeTileEntityContext
import net.minecraft.server.MinecraftServer

@CompileStatic
final class NativeTileEntityServerContext extends AbstractNativeServerContext implements CoreContext {

    private final NativeTileEntityContext tileEntityContext

    NativeTileEntityServerContext(NativeTileEntityContext tileEntityContext) {
        this.tileEntityContext = Objects.requireNonNull(tileEntityContext)
    }

    MinecraftServer getNativeServer() {
        return tileEntityContext.nativeTileEntity.world.minecraftServer
    }

}