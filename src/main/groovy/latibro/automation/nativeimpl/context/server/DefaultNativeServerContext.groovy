package latibro.automation.nativeimpl.context.server

import latibro.automation.core.context.CoreContext
import net.minecraft.server.MinecraftServer
import net.minecraftforge.fml.common.FMLCommonHandler

class DefaultNativeServerContext extends AbstractNativeServerContext implements CoreContext {

    @Override
    MinecraftServer getNativeServer() {
        return FMLCommonHandler.instance().getMinecraftServerInstance()
    }

}
