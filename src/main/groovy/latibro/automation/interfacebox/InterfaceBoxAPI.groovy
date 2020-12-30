package latibro.automation.interfacebox;

import latibro.automation.core.api.API;
import latibro.automation.core.lua.LuaMethod;

public interface InterfaceBoxAPI extends API {

    @LuaMethod
    String[] list();

    @LuaMethod
    API require(String name);

}
