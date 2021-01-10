package latibro.automation.integration.rail.api.vehicle.cab

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.integration.rail.api.vehicle.cab.controls.CabControls
import latibro.automation.integration.rail.api.vehicle.rollingstock.RollingStock

interface Cab {

    @LuaMethod
    CabControls getControls()

    @LuaMethod
    RollingStock getRollingStock()

}
