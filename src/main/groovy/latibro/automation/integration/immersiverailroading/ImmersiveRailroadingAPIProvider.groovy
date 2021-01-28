package latibro.automation.integration.immersiverailroading

import cam72cam.immersiverailroading.entity.Locomotive
import cam72cam.immersiverailroading.entity.LocomotiveDiesel
import latibro.automation.core.api.APIProvider
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.api.FeatureAPI
import latibro.automation.core.context.Context
import latibro.automation.integration.immersiverailroading.api.rollingstock.DieselLocomotiveAPIImpl
import latibro.automation.integration.immersiverailroading.api.rollingstock.LocomotiveAPIImpl
import latibro.automation.integration.immersiverailroading.api.rollingstock.RollingStockAPIImpl
import latibro.automation.integration.immersiverailroading.context.RollingStockContext

final class ImmersiveRailroadingAPIProvider implements APIProvider {

    @Override
    ContextAPI findContextAPI(Context context) {
        if (context instanceof RollingStockContext<LocomotiveDiesel>) {
            return new DieselLocomotiveAPIImpl(context)
        }
        if (context instanceof RollingStockContext<Locomotive>) {
            return new LocomotiveAPIImpl(context)
        }
        if (context instanceof RollingStockContext) {
            return new RollingStockAPIImpl(context)
        }
        return null
    }

    @Override
    List<String> getFeatureAPINames(Context context) {
        return null
    }

    @Override
    FeatureAPI findFeatureAPI(String name, Context context) {
        return null
    }

}