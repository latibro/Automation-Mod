package latibro.automation.integration.minecraft.context.entity


import latibro.automation.nativeimpl.context.entity.NativeEntityContext
import net.minecraft.entity.Entity

class EntityContextImpl<T extends Entity> implements EntityContext {

    @Delegate private final latibro.automation.core.context.entity.EntityContext entityContext

    EntityContextImpl(latibro.automation.core.context.entity.EntityContext entityContext) {
        this.entityContext = Objects.requireNonNull(entityContext)
    }

    T getMinecraftEntity() {
        def nativeEntityContext = (NativeEntityContext) entityContext
        return (T) nativeEntityContext.nativeEntity
    }

}
