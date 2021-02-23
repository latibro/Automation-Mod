package latibro.automation.nativeimpl.context.location

import groovy.transform.CompileStatic
import latibro.automation.core.LinkType
import latibro.automation.nativeimpl.context.entity.CoreEntityLinkContext
import latibro.automation.nativeimpl.context.world.CoreWorldLinkContext
import net.minecraft.util.math.BlockPos

@CompileStatic
final class EntityCoreLocationContext extends CoreLocationContext {

    private final CoreEntityLinkContext entity

    EntityCoreLocationContext(CoreEntityLinkContext entity) {
        this.entity = Objects.requireNonNull(entity)
    }

    @Override
    BlockPos getNativeLocation() {
        return entity.nativeEntity.position
    }

    @Override
    CoreWorldLinkContext getWorld() {
        return entity.world
    }

    @Override
    LinkType getLinkType() {
        return LinkType.DYNAMIC
    }

}
