package latibro.automation.integration.rail.api.vehicle.train

import latibro.automation.integration.rail.api.vehicle.rollingstock.RollingStock

interface Train {

    List<RollingStock> getRollingStockList()

    RollingStock getRollingStock(int index)

    int getRollingStockCount()

    // TrainControl getControl()

}