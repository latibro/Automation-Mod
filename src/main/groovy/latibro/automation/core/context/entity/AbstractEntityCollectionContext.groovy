package latibro.automation.core.context.entity


import latibro.automation.core.context.server.DefaultServerContext
import latibro.automation.core.context.server.ServerContext
import net.minecraft.entity.Entity

abstract class AbstractEntityCollectionContext<T extends Entity> implements EntityCollectionContext<T> {

    abstract Collection<T> getAllMinecraftEntity()

    @Override
    ServerContext getServerContext() {
        return new DefaultServerContext()
    }

}
