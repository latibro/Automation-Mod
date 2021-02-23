package latibro.automation.integration.immersiverailroading.context

import cam72cam.immersiverailroading.entity.EntityRollingStock
import latibro.automation.core.context.entity.EntityLinkContext

interface RollingStockLinkContext<T extends EntityRollingStock> extends EntityLinkContext {

    T getImmersiveRailroadingRollingStock()

}