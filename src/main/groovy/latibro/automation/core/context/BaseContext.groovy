package latibro.automation.core.context

import latibro.automation.core.capability.Capability
import latibro.automation.core.capability.CapabilityRegistry
import latibro.automation.core.source.SourceRegistry
import latibro.automation.lua.api.LuaAPI
import latibro.automation.lua.api.LuaAPIRegistry

abstract class BaseContext implements Context {

    abstract <T> T getBaseSource(Class<T> type)

    def <T> T getProvidedSource(Class<T> type) {
        def source = SourceRegistry.INSTANCE.getSource(type, this)
        return source
    }

    @Override
    <T> T getSource(Class<T> type) {
        def baseSource = getBaseSource(type)
        def providedSource = getProvidedSource(type)

        if (baseSource && providedSource) {
            // Both base and provided found
            throw new Exception("Both base and provided found")
        }

        if (baseSource) {
            // Only base found
            return baseSource
        }

        return providedSource
    }

    @Override
    <T extends Capability> T getCapability(Class<T> type) {
        return CapabilityRegistry.INSTANCE.getCapability(type, this)
    }

    @Override
    LuaAPI getLuaAPI(String name) {
        return LuaAPIRegistry.INSTANCE.getLuaAPI(name, this)
    }

}
