package latibro.automation.integration.immersiverailroading.api.rollingstock

import cam72cam.immersiverailroading.entity.EntityRollingStock
import latibro.automation.core.api.entity.BaseEntityLinkAPI
import latibro.automation.integration.immersiverailroading.context.RollingStockLinkContext

class RollingStockAPIImpl extends BaseEntityLinkAPI implements RollingStockAPI {

    RollingStockAPIImpl(RollingStockLinkContext context) {
        super(context)
    }

    RollingStockLinkContext<EntityRollingStock> getContext() {
        return super.context as RollingStockLinkContext<EntityRollingStock>
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
