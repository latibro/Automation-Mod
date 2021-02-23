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
import latibro.automation.integration.immersiverailroading.api.rollingstock.*
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
    List<String> getAPINames(API api) {
        def names = super.getAPINames(api)
        if (api instanceof CoreEntityLinkAPI) {
            names.add("immersiverailroading:rollingstock")
            names.add("immersiverailroading:locomotive")
            names.add("immersiverailroading:locomotivediesel")
        }
        return names
    }

    @Override
    boolean hasAPI(String name, API api) {
        if (api instanceof CoreEntityLinkAPI) {
            if (name == "immersiverailroading:rollingstock") {
                return true
            }
            if (name == "immersiverailroading:locomotive") {
                return true
            }
            if (name == "immersiverailroading:locomotivediesel") {
                return true
            }
        }
        return super.hasAPI(name, api)
    }

    @Override
    API getAPI(String name, API api) {
        if (hasAPI(name, api)) {
            if (api instanceof CoreEntityLinkAPI) {
                if (name == "immersiverailroading:rollingstock") {
                    return getAPI(RollingStockAPI, api)
                }
                if (name == "immersiverailroading:locomotive") {
                    return getAPI(LocomotiveAPI, api)
                }
                if (name == "immersiverailroading:locomotivediesel") {
                    return getAPI(DieselLocomotiveAPI, api)
                }
            }
        }
        return super.getAPI(name, api)
    }

    @Override
    API getAPI(Class<? extends API> cls, API api) {
        if (api instanceof ContextAPI) {
            if (cls == RollingStockAPI) {
                def context = ContextRegistry.getContext(RollingStockLinkContext, api.context)
                return APIRegistry.getAPI(context)
            }
            if (cls == LocomotiveAPI) {
                def context = ContextRegistry.getContext(RollingStockLinkContext<Locomotive>, api.context)
                return APIRegistry.getAPI(context)
            }
            if (cls == DieselLocomotiveAPI) {
                def context = ContextRegistry.getContext(RollingStockLinkContext<LocomotiveDiesel>, api.context)
                return APIRegistry.getAPI(context)
            }
        }
        return super.getAPI(cls, api)
    }

}