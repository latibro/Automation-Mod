package latibro.automation.core.context.server

import net.minecraft.server.MinecraftServer
import net.minecraftforge.fml.common.FMLCommonHandler

class DefaultServerContext extends AbstractServerContext {

    @Override
    MinecraftServer getMinecraftServer() {
        return FMLCommonHandler.instance().getMinecraftServerInstance()
    }

}
