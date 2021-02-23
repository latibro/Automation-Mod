package latibro.automation.integration.immersiverailroading.api.rollingstock


import cam72cam.immersiverailroading.entity.LocomotiveDiesel
import latibro.automation.integration.immersiverailroading.context.RollingStockLinkContext

class DieselLocomotiveAPIImpl extends LocomotiveAPIImpl implements DieselLocomotiveAPI {

    DieselLocomotiveAPIImpl(RollingStockLinkContext context) {
        super(context)
    }

    RollingStockLinkContext<LocomotiveDiesel> getContext() {
        return super.context as RollingStockLinkContext<LocomotiveDiesel>
    }

    @Override
    void startEngine() {
        setEngineRunning(true)
    }

    @Override
    void stopEngine() {
        setEngineRunning(false)
    }

    void setEngineRunning(boolean running) {
        context.immersiveRailroadingRollingStock.setTurnedOn(running)
    }

    @Override
    boolean isEngineRunning() {
        context.immersiveRailroadingRollingStock.isTurnedOn()
    }

}
