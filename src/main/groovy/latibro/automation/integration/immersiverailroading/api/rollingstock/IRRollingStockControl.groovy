package latibro.automation.integration.immersiverailroading.api.rollingstock

import cam72cam.immersiverailroading.entity.EntityRollingStock
import latibro.automation.integration.rail.api.vehicle.rollingstock.AbstractRollingStockControl

class IRRollingStockControl extends AbstractRollingStockControl {

    IRRollingStockControl(IRRollingStock rollingStock) {
        super(rollingStock)
    }

    protected EntityRollingStock getIREntity() {
        return (EntityRollingStock) ((IRRollingStock) rollingStock).getIREntity()
    }

}
