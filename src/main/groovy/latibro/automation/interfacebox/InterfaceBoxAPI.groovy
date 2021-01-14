package latibro.automation.interfacebox


import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.api.API
import latibro.automation.core.api.HostedAPI

interface InterfaceBoxAPI extends API {

    @LuaMethod(
            name = "listAPI",
            doc = "function() : array<name : string>"
    )
    Set<String> getAllAPIAsNameString();

    @LuaMethod(
            name = "getAPI",
            doc = "function(name : string) : userdata<API>"
    )
    HostedAPI getAPIByName(String name);

}
