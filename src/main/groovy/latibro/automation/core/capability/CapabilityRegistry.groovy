package latibro.automation.core.capability

import latibro.automation.core.context.Context

class CapabilityRegistry {

    static final CapabilityRegistry INSTANCE = new CapabilityRegistry()

    private final List<CapabilityProvider> providers = (List<CapabilityProvider>) []

    void register(CapabilityProvider provider) {
        providers.add(provider)
    }

    def <T extends Capability> List<T> findCapabilities(Class<T> type, Context context) {
        def capabilities = providers.findResults {
            it.getCapability(type, context)
        }.unique()
        return capabilities as List<T>
    }

    def <T extends Capability> T getCapability(Class<T> type, Context context) {
        def capabilities = findCapabilities(type, context)
        if (!capabilities) {
            throw null
        }
        if (capabilities?.size() > 1) {
            throw new Exception("Multiple (" + capabilities.size() + ") capabilities found: " + type)
        }
        return capabilities.first()
    }

}
