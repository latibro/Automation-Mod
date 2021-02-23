package latibro.automation.nativeimpl.context.world

import groovy.transform.CompileStatic
import latibro.automation.core.LinkType
import latibro.automation.nativeimpl.context.entity.CoreEntityLinkContext
import latibro.automation.nativeimpl.context.server.CoreServerLinkContext
import net.minecraft.world.World

@CompileStatic
final class EntityCoreWorldLinkContext extends CoreWorldLinkContext {

    private final CoreEntityLinkContext entity

    EntityCoreWorldLinkContext(CoreEntityLinkContext entity) {
        this.entity = Objects.requireNonNull(entity)
    }

    World getNativeWorld() {
        return entity.nativeEntity.world
    }

    @Override
    CoreServerLinkContext getServer() {
        return entity.server
    }

    @Override
    LinkType getLinkType() {
        return LinkType.DYNAMIC
    }

}