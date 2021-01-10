package latibro.automation.integration.rail.api.vehicle.rollingstock

import latibro.automation.core.api.APIHost
import latibro.automation.core.api.AbstractHostedAPI
import latibro.automation.integration.immersiverailroading.api.rollingstock.IRRollingStockAPI

import javax.annotation.Nonnull

class RollingStockAPIImpl extends AbstractHostedAPI implements RollingStockAPI {

    RollingStockAPIImpl(@Nonnull APIHost host) {
        super(host)
    }

    @Override
    @Nonnull
    RollingStock getRollingStockByUUID(@Nonnull String uuid) {
        return new IRRollingStockAPI(host).getRollingStockByUUID(uuid)
    }

}
