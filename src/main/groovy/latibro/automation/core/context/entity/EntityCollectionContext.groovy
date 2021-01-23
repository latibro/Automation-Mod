package latibro.automation.core.context.entity

import latibro.automation.core.context.Context
import latibro.automation.core.context.server.ServerContextProvider
import net.minecraft.entity.Entity

interface EntityCollectionContext<T extends Entity> extends Context, ServerContextProvider {

    Collection<T> getAllMinecraftEntity()

}
