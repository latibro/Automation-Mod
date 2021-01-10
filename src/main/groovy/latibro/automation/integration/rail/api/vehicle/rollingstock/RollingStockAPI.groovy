package latibro.automation.integration.rail.api.vehicle.rollingstock

import latibro.automation.api.core.lua.LuaMethod

interface RollingStockAPI {

    @LuaMethod(
            name = "getByUUID",
            usage = "function(uuid: string) : RollingStock"
    )
    RollingStock getRollingStockByUUID(String uuid)

}