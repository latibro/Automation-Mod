package latibro.automation.integration.rail.api.vehicle.cab.controls

import latibro.automation.integration.rail.api.vehicle.cab.Cab

class DynamicLinkedCabControlsImpl implements CabControls {

    private final Cab cab

    DynamicLinkedCabControlsImpl(Cab cab) {
        this.cab = cab
    }

    @Override
    Cab getCab() {
        return cab
    }

    private CabControls getImpl() {
        //TODO get entity from context
        //TODO find suitable impl (impl.worksWith())
        //TODO return suitable impl
        return cab.getControls()
    }

    @Override
    void setThrottleLevel(double level) {
        assert level >= -100
        assert level <= 100
        getImpl().setThrottleLevel(level)
    }

    @Override
    double getThrottleLevel() {
        return getImpl().getThrottleLevel()
    }

    @Override
    void setBrakeLevel(double level) {
        assert level >= 0
        assert level <= 100
        getImpl().setBrakeLevel(level)
    }

    @Override
    double getBrakeLevel() {
        return getImpl().getBrakeLevel()
    }

    @Override
    void setIgnitionState(boolean state) {
        getImpl().setIgnitionState(state)
    }

    @Override
    boolean getIgnitionState() {
        getImpl().getIgnitionState()
    }

    @Override
    void soundHorn() {
        getImpl().soundHorn()
    }

}
