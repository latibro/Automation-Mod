package latibro.automation.nativeimpl.context.entity.multi

import latibro.automation.core.LinkType
import net.minecraft.entity.Entity

final class InstanceCoreEntityMultiLinkContext extends CoreEntityMultiLinkContext {

    private final List<Entity> nativeEntityList

    InstanceCoreEntityMultiLinkContext(Collection<Entity> nativeEntityList) {
        this.nativeEntityList = Objects.requireNonNull(nativeEntityList)
    }

    List<Entity> getNativeEntityList() {
        return nativeEntityList
    }

    @Override
    LinkType getLinkType() {
        return LinkType.STATIC
    }

}
