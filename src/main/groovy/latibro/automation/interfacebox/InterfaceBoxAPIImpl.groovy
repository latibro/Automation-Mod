package latibro.automation.interfacebox

import latibro.automation.core.api.APIHost
import latibro.automation.core.api.AbstractHostedAPI
import latibro.automation.integration.minecraft.api.entity.EntityAPIImpl
import latibro.automation.integration.rail.api.vehicle.rollingstock.RollingStockAPIImpl

class InterfaceBoxAPIImpl extends AbstractHostedAPI implements InterfaceBoxAPI {

    InterfaceBoxAPIImpl(APIHost host) {
        super(host)
    }

    @Override
    List<String> findAllAvailableAPIAsStrings() {
        return ["entity"]
    }

    @Override
    Object getAPI(String name) {
        if (name == "entity") {
            return new EntityAPIImpl(host)
        }
        if (name == "rolling_stock") {
            return new RollingStockAPIImpl(host)
        }
        throw new IllegalArgumentException("Unknown API") //TODO better exception
    }

}
