package latibro.automation.interfacebox

import latibro.automation.api.core.lua.LuaMethod
import latibro.automation.core.api.API

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
    API getAPIByName(String name);

}
