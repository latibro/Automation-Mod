package latibro.automation.integration.rail.api.vehicle.cab

import latibro.automation.integration.rail.api.vehicle.rollingstock.RollingStock

abstract class AbstractCabImpl implements Cab {

    private final RollingStock rollingStock

    AbstractCabImpl(RollingStock rollingStock) {
        this.rollingStock = Objects.requireNonNull(rollingStock)
    }

    @Override
    RollingStock getRollingStock() {
        return rollingStock
    }

}
