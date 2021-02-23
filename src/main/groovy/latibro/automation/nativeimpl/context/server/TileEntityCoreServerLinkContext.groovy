package latibro.automation.nativeimpl.context.server

import groovy.transform.CompileStatic
import latibro.automation.nativeimpl.context.tileentity.CoreTileEntityLinkContext
import net.minecraft.server.MinecraftServer

@CompileStatic
final class TileEntityCoreServerLinkContext extends CoreServerLinkContext {

    private final CoreTileEntityLinkContext tileEntity

    TileEntityCoreServerLinkContext(CoreTileEntityLinkContext tileEntity) {
        this.tileEntity = Objects.requireNonNull(tileEntity)
    }

    MinecraftServer getNativeServer() {
        return tileEntity.nativeTileEntity.world.minecraftServer
    }

}