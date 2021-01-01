package latibro.automation.core.peripheral

import latibro.automation.core.context.ContextImpl
import net.minecraft.world.World

class PeripheralContext extends ContextImpl {

    private final Peripheral peripheral

    PeripheralContext(Peripheral peripheral) {
        this(peripheral, [:])
    }

    PeripheralContext(Peripheral peripheral, Map properties) {
        super(properties)
        this.peripheral = peripheral
    }

    Peripheral getPeripheral() {
        return peripheral
    }

    @Override
    World getWorld() {
        return getPeripheral().getWorld()
    }

}
