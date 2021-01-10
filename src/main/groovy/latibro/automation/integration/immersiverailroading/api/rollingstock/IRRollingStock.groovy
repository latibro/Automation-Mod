package latibro.automation.integration.immersiverailroading.api.rollingstock

import cam72cam.immersiverailroading.entity.EntityRollingStock
import cam72cam.mod.entity.ModdedEntity
import latibro.automation.integration.minecraft.api.entity.DirectEntity
import latibro.automation.integration.rail.api.vehicle.rollingstock.AbstractRollingStockImpl
import latibro.automation.integration.rail.api.vehicle.rollingstock.RollingStockControl

class IRRollingStock extends AbstractRollingStockImpl {

    protected IRRollingStock(DirectEntity entity) {
        super(entity)
        //assert IRRollingStock
    }

    protected EntityRollingStock getIREntity() {
        return (EntityRollingStock) ((ModdedEntity) entity.minecraftEntity).self
    }

    @Override
    RollingStockControl getControl() {
        return new IRRollingStockControl(this)
    }

}
