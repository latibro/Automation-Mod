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
    List<String> getAllLoadedAsUUIDString() {
        return null
    }

    @Override
    List<UUID> getAllLoadedAsUUID() {
        return null
    }

    @Override
    List<RollingStock> getAllLoaded() {
        return null
    }

    @Override
    @Nonnull
    RollingStock getByUUIDString(@Nonnull String uuid) {
        return new IRRollingStockAPI(host).getByUUIDString(uuid)
    }

    @Override
    RollingStock getByUUID(@Nonnull UUID uuid) {
        return null
    }

}
