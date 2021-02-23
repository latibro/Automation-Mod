package latibro.automation.integration.immersiverailroading

import cam72cam.immersiverailroading.entity.Locomotive
import cam72cam.immersiverailroading.entity.LocomotiveDiesel
import latibro.automation.core.context.AbstractContextProvider
import latibro.automation.core.context.Context
import latibro.automation.core.context.CoreContext
import latibro.automation.core.context.entity.EntityLinkContext
import latibro.automation.integration.immersiverailroading.context.RollingStockLinkContext
import latibro.automation.integration.immersiverailroading.context.RollingStockLinkContextImpl

final class ImmersiveRailroadingContextProvider extends AbstractContextProvider {

    @Override
    Context getContext(Class<? extends Context> cls, Context context) {
        if (context instanceof CoreContext) {
            if (context instanceof EntityLinkContext) {
                if (cls == RollingStockLinkContext<LocomotiveDiesel>) {
                    return new RollingStockLinkContextImpl<LocomotiveDiesel>(context)
                }
                if (cls == RollingStockLinkContext<Locomotive>) {
                    return new RollingStockLinkContextImpl<Locomotive>(context)
                }
                if (cls == RollingStockLinkContext) {
                    return new RollingStockLinkContextImpl(context)
                }
            }
        }
        return super.getContext(cls, context)
    }

}