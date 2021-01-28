package latibro.automation.core.context.entity

import latibro.automation.core.api.entity.EntityAPI
import latibro.automation.core.context.Context
import latibro.automation.core.context.position.PositionContextProvider
import latibro.automation.core.context.server.ServerContextProvider
import latibro.automation.core.context.world.WorldContextProvider

interface EntityContext extends Context<EntityAPI>, ServerContextProvider, WorldContextProvider, PositionContextProvider {

    UUID getUUID()

    boolean isLoaded()

}
