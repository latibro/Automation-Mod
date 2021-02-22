package latibro.automation.integration.immersiverailroading.api.rollingstock

import cam72cam.immersiverailroading.entity.EntityRollingStock
import latibro.automation.core.api.entity.BaseEntityLinkAPI
import latibro.automation.integration.immersiverailroading.context.RollingStockContext

class RollingStockAPIImpl extends BaseEntityLinkAPI implements RollingStockAPI {

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
