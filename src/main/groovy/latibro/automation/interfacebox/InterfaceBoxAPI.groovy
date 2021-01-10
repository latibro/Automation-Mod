package latibro.automation.interfacebox


import latibro.automation.api.core.lua.LuaMethod

interface InterfaceBoxAPI {

    @LuaMethod(
            name = "listAPI",
            doc = "function() : array<string>"
    )
    List<String> findAllAvailableAPIAsStrings();

    @LuaMethod(
            name = "getAPI",
            doc = "function(name : string) : API"
    )
    Object getAPI(String name);

}
