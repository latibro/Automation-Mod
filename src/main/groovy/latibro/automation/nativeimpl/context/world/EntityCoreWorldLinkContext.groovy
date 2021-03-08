package latibro.automation.nativeimpl.context.world

import latibro.automation.core.LinkType
import latibro.automation.nativeimpl.context.entity.CoreEntityLinkContext
import net.minecraft.world.World

class EntityCoreWorldLinkContext extends CoreWorldLinkContext {

    private final CoreEntityLinkContext entity

    EntityCoreWorldLinkContext(CoreEntityLinkContext entity) {
        this.entity = Objects.requireNonNull(entity)
    }

    @Override
    World getNativeWorld() {
        return entity.nativeEntity.world
    }

    @Override
    LinkType getLinkType() {
        return LinkType.DYNAMIC
    }

}
