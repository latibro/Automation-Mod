package latibro.automation.integration.rail.api.vehicle.rollingstock

abstract class AbstractRollingStockControl implements RollingStockControl {

    private final RollingStock rollingStock

    AbstractRollingStockControl(RollingStock rollingStock) {
        this.rollingStock = Objects.requireNonNull(rollingStock)
    }

    @Override
    RollingStock getRollingStock() {
        return rollingStock
    }

}
