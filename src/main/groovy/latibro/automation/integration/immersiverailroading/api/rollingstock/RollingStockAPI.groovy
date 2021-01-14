package latibro.automation.integration.immersiverailroading.api.rollingstock

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.api.API

interface RollingStockAPI extends API {

    @LuaMethod(
            name = "getAllLoadedAsUUID",
            usage = "function() : array<uuid : string>"
    )
    List<String> getAllLoadedAsUUIDString()

    @LuaMethod(
            name = "getByUUID",
            usage = "function(uuid: string) : RollingStockImpl"
    )
    RollingStock getByUUIDString(String uuid)

}