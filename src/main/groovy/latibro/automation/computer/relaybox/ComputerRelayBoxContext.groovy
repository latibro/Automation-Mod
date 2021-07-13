package latibro.automation.computer.relaybox

import latibro.automation.computer.relaybox.block.ComputerRelayBoxBlockEntity
import latibro.automation.core.context.BaseContext

class ComputerRelayBoxContext extends BaseContext {

    private final ComputerRelayBoxBlockEntity relayBox

    private ComputerRelayBoxContext(ComputerRelayBoxBlockEntity relayBox) {
        this.relayBox = relayBox
    }

    def <T> T getBaseSource(Class<T> type) {
        if (ComputerRelayBoxBlockEntity.isAssignableFrom(type)) {
            return relayBox as T
        }
        return null
    }

    ComputerRelayBoxSourceSlotContext getSourceSlot(Integer index) {
        def sourceSlot = relayBox.getSourceSlot(index)
        return ComputerRelayBoxSourceSlotContext.create(this, sourceSlot)
    }

    static ComputerRelayBoxContext create(ComputerRelayBoxBlockEntity relayBox) {
        return new ComputerRelayBoxContext(relayBox)
    }

}
