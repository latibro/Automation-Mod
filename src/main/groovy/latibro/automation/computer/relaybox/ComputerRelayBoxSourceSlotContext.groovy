package latibro.automation.computer.relaybox

import latibro.automation.computer.relaybox.block.ComputerRelayBoxBlockEntity
import latibro.automation.core.context.BaseContext
import latibro.automation.core.source.reference.SourceReference

class ComputerRelayBoxSourceSlotContext extends BaseContext {

    private final ComputerRelayBoxBlockEntity relayBox

    ComputerRelayBoxSourceSlotContext(ComputerRelayBoxBlockEntity relayBox) {
        this.relayBox = relayBox
    }

    def <T> T getBaseSource(Class<T> type) {
        if (SourceReference.isAssignableFrom(type)) {
            def sourceRef = relayBox.getSourceObject()

            if (type.isInstance(sourceRef)) {
                return sourceRef as T
            }
        }

        return null
    }

}
