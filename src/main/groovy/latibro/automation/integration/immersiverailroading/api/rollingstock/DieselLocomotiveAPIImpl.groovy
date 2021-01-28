package latibro.automation.integration.immersiverailroading.api.rollingstock


import cam72cam.immersiverailroading.entity.LocomotiveDiesel
import latibro.automation.integration.immersiverailroading.context.RollingStockContext

class DieselLocomotiveAPIImpl extends LocomotiveAPIImpl implements DieselLocomotiveAPI {

    DieselLocomotiveAPIImpl(RollingStockContext context) {
        super(context)
    }

    protected RollingStockContext<LocomotiveDiesel> getContext() {
        return super.context as RollingStockContext<LocomotiveDiesel>
    }

    @Override
    void turnOn() {
        setTurnedOn(true)
    }

    @Override
    void turnOff() {
        setTurnedOn(false)
    }

    @Override
    void setTurnedOn(boolean state) {
        context.immersiveRailroadingRollingStock.setTurnedOn(state)
    }

    @Override
    boolean isTurnedOn() {
        context.immersiveRailroadingRollingStock.isTurnedOn()
    }

}
