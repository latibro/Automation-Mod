package latibro.automation.integration.immersiverailroading.api.rollingstock.cab.controls

import cam72cam.immersiverailroading.entity.Locomotive
import cam72cam.immersiverailroading.entity.LocomotiveDiesel
import latibro.automation.integration.immersiverailroading.api.rollingstock.cab.IRCabImpl
import latibro.automation.integration.rail.api.vehicle.cab.controls.AbstractCabControlsImpl

class IRCabControlsImpl extends AbstractCabControlsImpl {

    IRCabControlsImpl(IRCabImpl cab) {
        super(cab)
    }

    Locomotive getIRLocomotive() {
        return (Locomotive) ((IRCabImpl) cab).getIRLocomotive()
    }

    @Override
    void setThrottleLevel(double level) {
        assert level >= -100
        assert level <= 100
        getIRLocomotive().setThrottle((level / 100d) as float)
    }

    @Override
    double getThrottleLevel() {
        return getIRLocomotive().getThrottle() * 100d
    }

    @Override
    void setBrakeLevel(double level) {
        assert level >= 0
        assert level <= 100
        getIRLocomotive().setAirBrake((level / 100d) as float)
    }

    @Override
    double getBrakeLevel() {
        return getIRLocomotive().getAirBrake() * 100d
    }

    @Override
    void setIgnitionState(boolean state) {
        ((LocomotiveDiesel) getIRLocomotive()).setTurnedOn(state)
    }

    @Override
    boolean getIgnitionState() {
        ((LocomotiveDiesel) getIRLocomotive()).isTurnedOn()
    }

    @Override
    void soundHorn() {
        getIRLocomotive().setHorn(40, null)
    }

}
