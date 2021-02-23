package latibro.automation.nativeimpl.context.server


import net.minecraft.server.MinecraftServer
import net.minecraftforge.fml.common.FMLCommonHandler

final class DefaultCoreServerLinkContext extends CoreServerLinkContext {

    @Override
    MinecraftServer getNativeServer() {
        return FMLCommonHandler.instance().getMinecraftServerInstance()
    }

}
