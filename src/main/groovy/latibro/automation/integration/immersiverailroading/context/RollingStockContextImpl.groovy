package latibro.automation.integration.immersiverailroading.context

import cam72cam.immersiverailroading.entity.EntityRollingStock
import cam72cam.mod.entity.ModdedEntity
import latibro.automation.core.context.entity.EntityContext
import latibro.automation.nativeimpl.context.entity.NativeEntityContext

class RollingStockContextImpl<T extends EntityRollingStock> implements RollingStockContext<T> {

    @Delegate private final EntityContext entityContext

    RollingStockContextImpl(EntityContext entityContext) {
        this.entityContext = entityContext
    }

    @Override
    T getImmersiveRailroadingRollingStock() {
        def nativeEntityContext = (NativeEntityContext) entityContext
        def moddedEntity = (ModdedEntity) nativeEntityContext.nativeEntity
        return (T) moddedEntity.self
    }

}
