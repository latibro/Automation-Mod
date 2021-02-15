package latibro.automation.integration.immersiverailroading

import cam72cam.immersiverailroading.entity.Locomotive
import cam72cam.immersiverailroading.entity.LocomotiveDiesel
import latibro.automation.core.context.AbstractContextProvider
import latibro.automation.core.context.Context
import latibro.automation.core.context.CoreContext
import latibro.automation.core.context.entity.EntityContext
import latibro.automation.integration.immersiverailroading.context.RollingStockContext
import latibro.automation.integration.immersiverailroading.context.RollingStockContextImpl

final class ImmersiveRailroadingContextProvider extends AbstractContextProvider {

    @Override
    Context getContext(Class<? extends Context> cls, Context context) {
        if (context instanceof CoreContext) {
            if (context instanceof EntityContext) {
                if (cls == RollingStockContext<LocomotiveDiesel>) {
                    return new RollingStockContextImpl<LocomotiveDiesel>(context)
                }
                if (cls == RollingStockContext<Locomotive>) {
                    return new RollingStockContextImpl<Locomotive>(context)
                }
                if (cls == RollingStockContext) {
                    return new RollingStockContextImpl(context)
                }
            }
        }
        return super.getContext(cls, context)
    }

}