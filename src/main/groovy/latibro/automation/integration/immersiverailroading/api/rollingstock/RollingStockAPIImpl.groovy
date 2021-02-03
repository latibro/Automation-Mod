package latibro.automation.integration.immersiverailroading.api.rollingstock

import cam72cam.immersiverailroading.entity.EntityRollingStock
import latibro.automation.core.api.entity.EntityAPIImpl
import latibro.automation.integration.immersiverailroading.context.RollingStockContext

class RollingStockAPIImpl extends EntityAPIImpl implements RollingStockAPI {

    RollingStockAPIImpl(RollingStockContext context) {
        super(context)
    }

    protected RollingStockContext<EntityRollingStock> getContext() {
        return super.context as RollingStockContext<EntityRollingStock>
    }

    @Override
    void setTag(String tag) {
        context.immersiveRailroadingRollingStock.tag = tag
    }

    @Override
    String getTag() {
        return context.immersiveRailroadingRollingStock.tag
    }

}
