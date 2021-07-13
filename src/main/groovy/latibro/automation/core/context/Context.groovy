package latibro.automation.core.context


import latibro.automation.core.capability.Capability
import latibro.automation.lua.api.LuaAPI

interface Context {

    def <T> T getSource(Class<T> type)

    def <T extends Capability> T getCapability(Class<T> type)

    LuaAPI getLuaAPI(String name)

}
