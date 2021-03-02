package latibro.automation.nativeimpl.context.location

import groovy.transform.CompileStatic
import latibro.automation.core.LinkType
import latibro.automation.nativeimpl.context.chunk.CoreChunkLinkContext
import latibro.automation.nativeimpl.context.chunk.EntityCoreChunkLinkContext
import latibro.automation.nativeimpl.context.entity.CoreEntityLinkContext
import latibro.automation.nativeimpl.context.world.CoreWorldLinkContext
import net.minecraft.util.math.BlockPos

@CompileStatic
final class EntityCoreLocationLinkContext extends CoreLocationLinkContext {

    private final CoreEntityLinkContext entity

    EntityCoreLocationLinkContext(CoreEntityLinkContext entity) {
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

    @Override
    CoreChunkLinkContext getChunk() {
        return new EntityCoreChunkLinkContext(entity)
    }

}
