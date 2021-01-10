package latibro.automation.integration.rail.api

import latibro.automation.integration.rail.api.vehicle.rollingstock.RollingStockAPI

interface RailAPI {

    RollingStockAPI rollingStock()

}