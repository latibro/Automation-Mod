package latibro.automation.integration.immersiverailroading

import cam72cam.immersiverailroading.entity.Locomotive
import cam72cam.immersiverailroading.entity.LocomotiveDiesel
import latibro.automation.core.context.Context
import latibro.automation.core.context.ContextProvider
import latibro.automation.core.context.entity.EntityContext
import latibro.automation.integration.immersiverailroading.context.RollingStockContextImpl

final class ImmersiveRailroadingContextProvider implements ContextProvider {

    @Override
    List<String> getSubContextNames(Context context) {
        def names = []
        if (context instanceof EntityContext) {
            names.add("immersiverailroading.rollingstock")
            names.add("immersiverailroading.rollingstock.locomotive")
            names.add("immersiverailroading.rollingstock.locomotive.diesel")
        }
        return names
    }

    @Override
    Context findSubContext(String name, Context context) {
        if (context instanceof EntityContext) {
            if (name == "immersiverailroading.rollingstock") {
                return new RollingStockContextImpl(context)
            }
            if (name == "immersiverailroading.rollingstock.locomotive") {
                return new RollingStockContextImpl<Locomotive>(context)
            }
            if (name == "immersiverailroading.rollingstock.locomotive.diesel") {
                return new RollingStockContextImpl<LocomotiveDiesel>(context)
            }
        }
        return null
    }

}