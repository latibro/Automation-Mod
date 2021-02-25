package latibro.automation.integration.landofsignals

import latibro.automation.api.API
import latibro.automation.api.core.lua.LuaMethod

interface SignalAPI extends API {

    @LuaMethod
    List<String> getStates()

    @LuaMethod
    String getState()

    @LuaMethod
    void setState(String state)

}