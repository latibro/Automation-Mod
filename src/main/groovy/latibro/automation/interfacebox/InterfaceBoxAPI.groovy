package latibro.automation.interfacebox

import latibro.automation.core.api.API
import latibro.automation.core.lua.LuaMethod

interface InterfaceBoxAPI extends API {

    @LuaMethod
    String[] list();

    @LuaMethod
    API require(String name);

}
