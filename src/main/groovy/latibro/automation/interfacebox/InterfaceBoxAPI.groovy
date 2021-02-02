package latibro.automation.interfacebox

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.api.FeatureAPI
import latibro.automation.core.api.PeripheralAPI
import latibro.automation.core.api.location.LocationAPI
import latibro.automation.core.api.server.ServerAPI
import latibro.automation.core.api.world.WorldAPI

interface InterfaceBoxAPI extends PeripheralAPI {

    @LuaMethod
    LocationAPI getLocation()

    @LuaMethod
    WorldAPI getWorld()

    @LuaMethod
    ServerAPI getServer()

    @LuaMethod(
            name = "listAPI",
            doc = "function() : array<name : string>"
    )
    String[] getAPINames();

    @LuaMethod(
            name = "getAPI",
            doc = "function(name : string) : userdata<API>"
    )
    FeatureAPI getAPI(String name);

}
