package latibro.automation.integration.rail.api.vehicle.cab.instruments

import latibro.automation.integration.rail.api.vehicle.cab.Cab

abstract class AbstractCabInstrumentsImpl implements CabInstruments {

    private final Cab cab

    AbstractCabInstrumentsImpl(Cab cab) {
        this.cab = Objects.requireNonNull(cab)
    }

    @Override
    Cab getCab() {
        return cab
    }

}
