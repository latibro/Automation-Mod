package latibro.automation.core.context.server

import latibro.automation.core.context.entity.AbstractEntityCollectionContext
import latibro.automation.core.context.entity.EntityCollectionContext
import net.minecraft.server.MinecraftServer

abstract class AbstractServerContext implements ServerContext {

    @Override
    abstract MinecraftServer getMinecraftServer()

    @Override
    EntityCollectionContext getLoadedEntitiesContext() {
        return new AbstractEntityCollectionContext() {
            @Override
            Collection getAllMinecraftEntity() {
                def entities = []
                minecraftServer.worlds.findResults { it }.each { entities + it.loadedEntityList }
                return entities
            }
        }
    }

}