package latibro.automation.integration.rail.api.vehicle.rollingstock

import latibro.automation.integration.minecraft.api.entity.Entity
import latibro.automation.integration.minecraft.api.entity.EntityWrapper

abstract class AbstractRollingStockImpl extends EntityWrapper implements RollingStock {

    AbstractRollingStockImpl(Entity entity) {
        super(entity)
    }

}
