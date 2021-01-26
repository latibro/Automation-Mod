package latibro.automation.core.api.entity

import latibro.automation.core.api.API
import latibro.automation.core.api.position.PositionAPI
import latibro.automation.core.api.position.PositionAPIImpl
import latibro.automation.core.context.entity.EntityContext
import latibro.automation.integration.immersiverailroading.api.rollingstock.DieselLocomotiveAPIImpl
import latibro.automation.integration.immersiverailroading.api.rollingstock.LocomotiveAPIImpl
import latibro.automation.integration.immersiverailroading.api.rollingstock.RollingStockAPIImpl
import latibro.automation.integration.minecraft.api.entity.LivingEntityAPIImpl

class EntityAPIImpl implements EntityAPI {

    private final EntityContext context

    EntityAPIImpl(EntityContext context) {
        this.context = Objects.requireNonNull(context)
    }

    protected EntityContext getContext() {
        return context
    }

    @Override
    boolean isLoaded() {
        return context.isEntityLoaded()
    }

    @Override
    String getUUID() {
        return context.entityUUID?.toString()
    }

    @Override
    PositionAPI getPosition() {
        return new PositionAPIImpl(context.positionContext)
    }

    @Override
    EntityAPI asType(String name) {
        //TODO move API to context
        if (name == "immersive_railroading.rolling_stock") {
            return new RollingStockAPIImpl(context)
        }
        if (name == "immersive_railroading.locomotive") {
            return new LocomotiveAPIImpl(context)
        }
        if (name == "immersive_railroading.diesel_locomotive") {
            return new DieselLocomotiveAPIImpl(context)
        }
        throw new NullPointerException()
    }

    @Override
    API getAPI(String name) {
        if (name == "minecraft.entity_control") {
            return new LivingEntityAPIImpl(context)
        }
        throw new NullPointerException()
    }

}
