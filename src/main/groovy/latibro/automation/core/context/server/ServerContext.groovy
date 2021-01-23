package latibro.automation.core.context.server

import latibro.automation.core.context.Context
import latibro.automation.core.context.entity.LoadedEntitiesContextProvider
import net.minecraft.server.MinecraftServer

interface ServerContext extends Context, LoadedEntitiesContextProvider {

    MinecraftServer getMinecraftServer()

}