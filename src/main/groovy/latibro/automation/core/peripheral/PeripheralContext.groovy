package latibro.automation.core.peripheral

import latibro.automation.core.context.ContextImpl
import net.minecraft.world.World

public class PeripheralContext extends ContextImpl {

    private final Peripheral peripheral;

    public PeripheralContext(Peripheral peripheral) {
        this(peripheral, null);
    }

    public PeripheralContext(Peripheral peripheral, Map properties) {
        super(properties);
        this.peripheral = peripheral;
    }

    public Peripheral getPeripheral() {
        return peripheral;
    }

    @Override
    public World getWorld() {
        return getPeripheral().getWorld();
    }

}
