package latibro.automation.integration.immersiverailroading.api.rollingstock.cab

import cam72cam.immersiverailroading.entity.Locomotive
import latibro.automation.integration.immersiverailroading.api.rollingstock.IRRollingStock
import latibro.automation.integration.immersiverailroading.api.rollingstock.cab.controls.IRCabControlsImpl
import latibro.automation.integration.rail.api.vehicle.cab.AbstractCabImpl
import latibro.automation.integration.rail.api.vehicle.cab.controls.CabControls

class IRCabImpl extends AbstractCabImpl {

    IRCabImpl(IRRollingStock rollingStock) {
        super(rollingStock)
    }

    Locomotive getIRLocomotive() {
        return (Locomotive) ((IRRollingStock) rollingStock).getIRRollingStock()
    }

    @Override
    CabControls getControls() {
        return new IRCabControlsImpl(this)
    }

}
