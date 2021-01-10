package latibro.automation.integration.rail.api.vehicle.rollingstock

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.integration.minecraft.api.entity.Entity

interface RollingStock extends Entity {

    @LuaMethod
    RollingStockControl getControl()

    //TODO speed

    //TODO coupled (front/back)
    //TODO train

}
