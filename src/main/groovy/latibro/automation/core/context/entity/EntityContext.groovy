package latibro.automation.core.context.entity

import latibro.automation.core.context.Context
import latibro.automation.core.context.position.PositionContextProvider
import latibro.automation.core.context.server.ServerContextProvider
import latibro.automation.core.context.world.WorldContextProvider
import net.minecraft.entity.Entity

interface EntityContext<T extends Entity> extends Context, ServerContextProvider, WorldContextProvider, PositionContextProvider {

    T getMinecraftEntity()

}
