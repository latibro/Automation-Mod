package latibro.automation.core.context.server

import groovy.transform.CompileStatic
import latibro.automation.core.context.entity.AbstractEntityCollectionContext
import latibro.automation.core.context.entity.EntityCollectionContext
import net.minecraft.server.MinecraftServer

@CompileStatic
abstract class AbstractServerContext implements ServerContext {

    @Override
    abstract MinecraftServer getMinecraftServer()

    @Override
    EntityCollectionContext getLoadedEntitiesContext() {
        return new AbstractEntityCollectionContext() {
            @Override
            Collection getAllMinecraftEntity() {
                def entities = []
                minecraftServer.worlds.findResults { it }.each { entities.addAll(it.loadedEntityList) }
                return entities
            }
        }
    }

}