package latibro.automation.nativeimpl.context.entity


import latibro.automation.core.context.CoreContext
import net.minecraft.entity.Entity

final class NativeStaticInstanceEntityContext extends AbstractNativeEntityContext implements CoreContext {

    private final Entity nativeEntity

    NativeStaticInstanceEntityContext(Entity nativeEntity) {
        this.nativeEntity = nativeEntity
    }

    Entity getNativeEntity() {
        return nativeEntity
    }

}
