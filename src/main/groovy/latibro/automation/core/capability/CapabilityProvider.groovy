package latibro.automation.core.capability

import latibro.automation.core.context.Context

interface CapabilityProvider {

    def <T extends Capability> T getCapability(Class<T> type, Context context)

}