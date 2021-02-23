package latibro.automation.nativeimpl.context.entity

import latibro.automation.core.LinkType
import net.minecraft.entity.Entity

final class InstanceCoreEntityLinkContext extends CoreEntityLinkContext {

    private final Entity nativeEntity

    InstanceCoreEntityLinkContext(Entity nativeEntity) {
        this.nativeEntity = Objects.requireNonNull(nativeEntity)
    }

    Entity getNativeEntity() {
        return nativeEntity
    }

    @Override
    LinkType getLinkType() {
        return LinkType.STATIC
    }

}
