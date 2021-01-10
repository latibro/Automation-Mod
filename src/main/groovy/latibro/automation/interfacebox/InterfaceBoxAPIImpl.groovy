package latibro.automation.interfacebox

import latibro.automation.core.api.APIHost
import latibro.automation.core.api.AbstractHostedAPI
import latibro.automation.core.api.HostedAPI
import latibro.automation.integration.immersiverailroading.api.rollingstock.IRRollingStockAPI
import latibro.automation.integration.minecraft.api.entity.EntityAPIImpl
import net.minecraftforge.fml.common.Loader

class InterfaceBoxAPIImpl extends AbstractHostedAPI implements InterfaceBoxAPI {

    private final Map<String, HostedAPI> apis = [:]

    InterfaceBoxAPIImpl(APIHost host) {
        super(host)

        apis.put("entity", new EntityAPIImpl(host))

        if (Loader.isModLoaded("immersiverailroading")) {
            apis.put("rolling_stock", new IRRollingStockAPI(host))
        }
    }

    @Override
    Set<String> getAllNameOfAPIAsString() {
        return apis.keySet().asUnmodifiable()
    }

    @Override
    HostedAPI getAPIByName(String name) {
        def api = apis.get(name)
        if (api) {
            return api
        }
        throw new IllegalArgumentException("Unknown API") //TODO better exception
    }

}
