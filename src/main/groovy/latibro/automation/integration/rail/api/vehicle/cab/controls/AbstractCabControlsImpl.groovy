package latibro.automation.integration.rail.api.vehicle.cab.controls

import latibro.automation.integration.rail.api.vehicle.cab.Cab

abstract class AbstractCabControlsImpl implements CabControls {

    private final Cab cab

    AbstractCabControlsImpl(Cab cab) {
        this.cab = Objects.requireNonNull(cab)
    }

    @Override
    Cab getCab() {
        return cab
    }

}
