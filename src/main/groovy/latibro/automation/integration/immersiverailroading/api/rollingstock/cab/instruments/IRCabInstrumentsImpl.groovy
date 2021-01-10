package latibro.automation.integration.immersiverailroading.api.rollingstock.cab.instruments

import cam72cam.immersiverailroading.entity.Locomotive
import latibro.automation.integration.immersiverailroading.api.rollingstock.cab.IRCabImpl
import latibro.automation.integration.rail.api.vehicle.cab.instruments.AbstractCabInstrumentsImpl

class IRCabInstrumentsImpl extends AbstractCabInstrumentsImpl {

    IRCabInstrumentsImpl(IRCabImpl cab) {
        super(cab)
    }

    Locomotive getIRLocomotive() {
        return (Locomotive) ((IRCabImpl) cab).getIRLocomotive()
    }

    @Override
    double getSpeed() {
        return getIRLocomotive().getCurrentSpeed().metric()
    }

    @Override
    double getFuelLevel() {
        return getIRLocomotive().getPercentLiquidFull()
    }

}
