package latibro.automation.integration.rail.api.vehicle.rollingstock

import latibro.automation.api.core.lua.LuaMethod

import javax.annotation.Nonnull

interface RollingStockAPI {

    @LuaMethod(
            name = "getAllLoadedAsUUID",
            usage = "function() : array<uuid : string>"
    )
    List<String> getAllLoadedAsUUIDString()

    List<UUID> getAllLoadedAsUUID()

    List<RollingStock> getAllLoaded()

    @LuaMethod(
            name = "getByUUID",
            usage = "function(uuid: string) : RollingStock"
    )
    RollingStock getByUUIDString(String uuid)

    RollingStock getByUUID(@Nonnull UUID uuid)

}