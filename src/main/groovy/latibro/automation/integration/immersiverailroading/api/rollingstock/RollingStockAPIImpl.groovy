package latibro.automation.integration.immersiverailroading.api.rollingstock

import cam72cam.immersiverailroading.entity.EntityRollingStock
import latibro.automation.core.api.entity.BaseEntityAPI
import latibro.automation.integration.immersiverailroading.context.RollingStockContext

class RollingStockAPIImpl extends BaseEntityAPI implements RollingStockAPI {

    RollingStockAPIImpl(RollingStockContext context) {
        super(context)
    }

    RollingStockContext<EntityRollingStock> getContext() {
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
