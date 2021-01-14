package latibro.automation.integration.immersiverailroading.api.rollingstock

import cam72cam.immersiverailroading.entity.EntityRollingStock
import cam72cam.mod.entity.ModdedEntity
import latibro.automation.integration.minecraft.api.entity.Entity
import latibro.automation.integration.minecraft.api.entity.EntityWrapper

class RollingStockImpl<E extends EntityRollingStock> extends EntityWrapper implements RollingStock {

    protected RollingStockImpl(Entity entity) {
        super(entity)
    }

    protected E getIREntity() {
        return (E) ((ModdedEntity) entity.minecraftEntity).self
    }

}
