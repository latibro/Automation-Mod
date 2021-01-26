package latibro.automation.integration.immersiverailroading.api.rollingstock

import cam72cam.immersiverailroading.entity.EntityRollingStock
import latibro.automation.core.api.entity.EntityAPIImpl
import latibro.automation.core.context.entity.EntityContext
import latibro.automation.integration.immersiverailroading.context.RollingStockContext
import latibro.automation.integration.immersiverailroading.context.RollingStockContextTrait

class RollingStockAPIImpl<T extends EntityRollingStock> extends EntityAPIImpl implements RollingStockAPI {

    RollingStockAPIImpl(EntityContext context) {
        super(context)
    }

    protected RollingStockContext<T> getContext() {
        return super.context as RollingStockContextTrait<T>
    }

}
