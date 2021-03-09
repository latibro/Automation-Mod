package latibro.automation.integration.immersiverailroading

import cam72cam.immersiverailroading.entity.Locomotive
import cam72cam.immersiverailroading.entity.LocomotiveDiesel
import latibro.automation.api.API
import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.AbstractAPIProvider
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.api.entity.CoreEntityLinkAPI
import latibro.automation.core.context.Context
import latibro.automation.core.context.ContextRegistry
import latibro.automation.integration.immersiverailroading.api.rollingstock.DieselLocomotiveAPIImpl
import latibro.automation.integration.immersiverailroading.api.rollingstock.LocomotiveAPIImpl
import latibro.automation.integration.immersiverailroading.api.rollingstock.RollingStockAPIImpl
import latibro.automation.integration.immersiverailroading.context.RollingStockLinkContext

final class ImmersiveRailroadingAPIProvider extends AbstractAPIProvider {

    @Override
    API getAPI(Context context) {
        if (context instanceof RollingStockLinkContext<LocomotiveDiesel>) {
            return new DieselLocomotiveAPIImpl(context)
        }
        if (context instanceof RollingStockLinkContext<Locomotive>) {
            return new LocomotiveAPIImpl(context)
        }
        if (context instanceof RollingStockLinkContext) {
            return new RollingStockAPIImpl(context)
        }
        return super.getAPI(context)
    }

    @Override
    List<String> getAPINames(API providingApi) {
        if (providingApi instanceof CoreEntityLinkAPI) {
            return ["immersiverailroading:rollingstock",
                    "immersiverailroading:locomotive",
                    "immersiverailroading:locomotivediesel"]
        }
        return []
    }

    @Override
    API getAPI(String name, API providingApi) {
        if (hasAPI(name, providingApi)) {
            def providingContext = ((ContextAPI) providingApi).context
            if (name == "immersiverailroading:rollingstock") {
                def context = ContextRegistry.getContext(RollingStockLinkContext, providingContext)
                return APIRegistry.getAPI(context)
            }
            if (name == "immersiverailroading:locomotive") {
                def context = ContextRegistry.getContext(RollingStockLinkContext<Locomotive>, providingContext)
                return APIRegistry.getAPI(context)
            }
            if (name == "immersiverailroading:locomotivediesel") {
                def context = ContextRegistry.getContext(RollingStockLinkContext<LocomotiveDiesel>, providingContext)
                return APIRegistry.getAPI(context)
            }
        }
        return null
    }

}