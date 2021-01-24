package latibro.automation.integration.immersiverailroading.api.rollingstock

import cam72cam.immersiverailroading.entity.EntityRollingStock
import latibro.automation.integration.immersiverailroading.context.RollingStockContext
import latibro.automation.integration.minecraft.api.entity.EntityAPIImpl

class RollingStockAPIImpl<E extends EntityRollingStock> extends EntityAPIImpl implements RollingStockAPI {

    RollingStockAPIImpl(RollingStockContext<E> context) {
        super(context)
    }

    protected RollingStockContext<E> getContext() {
        return super.context as RollingStockContext<E>
    }

}
