package latibro.automation.integration.immersiverailroading.context

import cam72cam.immersiverailroading.entity.EntityRollingStock
import cam72cam.mod.entity.ModdedEntity
import latibro.automation.core.context.entity.EntityContext

class RollingStockContextImpl<T extends EntityRollingStock> implements RollingStockContext<T> {

    @Delegate private final EntityContext<ModdedEntity> context

    RollingStockContextImpl(EntityContext<ModdedEntity> context) {
        this.context = Objects.requireNonNull(context)
    }

    @Override
    T getImmersiveRailroadingRollingStock() {
        return (T) minecraftEntity.self
    }

}
