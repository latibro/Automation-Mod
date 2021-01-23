package latibro.automation.core.context.world

import latibro.automation.core.context.Context
import latibro.automation.core.context.entity.LoadedEntitiesContextProvider
import latibro.automation.core.context.position.PositionContext
import latibro.automation.core.context.server.ServerContextProvider
import net.minecraft.world.World

interface WorldContext extends Context, ServerContextProvider, LoadedEntitiesContextProvider {

    World getMinecraftWorld()

    PositionContext getPositionContextByXYZ(double x, double y, double z)

}
