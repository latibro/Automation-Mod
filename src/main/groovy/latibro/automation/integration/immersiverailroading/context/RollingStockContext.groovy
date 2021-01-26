package latibro.automation.integration.immersiverailroading.context

import cam72cam.immersiverailroading.entity.EntityRollingStock
import latibro.automation.core.context.entity.EntityContext

interface RollingStockContext<T extends EntityRollingStock> extends EntityContext {

    T getImmersiveRailroadingRollingStock()

}