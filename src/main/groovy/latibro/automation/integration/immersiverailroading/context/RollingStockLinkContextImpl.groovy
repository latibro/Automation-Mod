package latibro.automation.integration.immersiverailroading.context

import cam72cam.immersiverailroading.entity.EntityRollingStock
import cam72cam.mod.entity.ModdedEntity
import latibro.automation.core.context.entity.EntityLinkContext
import latibro.automation.nativeimpl.context.entity.CoreEntityLinkContext

class RollingStockLinkContextImpl<T extends EntityRollingStock> implements RollingStockLinkContext<T> {

    @Delegate
    private final EntityLinkContext entityContext

    RollingStockLinkContextImpl(EntityLinkContext entityContext) {
        this.entityContext = entityContext
    }

    @Override
    T getImmersiveRailroadingRollingStock() {
        def nativeEntityContext = (CoreEntityLinkContext) entityContext
        def moddedEntity = (ModdedEntity) nativeEntityContext.nativeEntity
        return (T) moddedEntity.self
    }

}
