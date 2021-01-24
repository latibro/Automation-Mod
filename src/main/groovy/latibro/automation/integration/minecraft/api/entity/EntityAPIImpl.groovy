package latibro.automation.integration.minecraft.api.entity

import cam72cam.mod.entity.ModdedEntity
import groovy.transform.CompileStatic
import latibro.automation.core.context.entity.AbstractEntityContext
import latibro.automation.core.context.entity.EntityContext
import latibro.automation.integration.immersiverailroading.api.rollingstock.DieselLocomotiveAPIImpl
import latibro.automation.integration.immersiverailroading.api.rollingstock.LocomotiveAPIImpl
import latibro.automation.integration.immersiverailroading.api.rollingstock.RollingStockAPIImpl
import latibro.automation.integration.immersiverailroading.context.RollingStockContextImpl
import latibro.automation.integration.minecraft.api.position.PositionAPI
import latibro.automation.integration.minecraft.api.position.PositionAPIImpl
import net.minecraft.entity.Entity

@CompileStatic
class EntityAPIImpl implements EntityAPI {

    private final EntityContext<Entity> context

    EntityAPIImpl(EntityContext context) {
        this.context = Objects.requireNonNull(context)
    }

    EntityAPIImpl(Entity minecraftEntity) {
        this(new AbstractEntityContext() {
            @Override
            Entity getMinecraftEntity() {
                return minecraftEntity
            }
        })
    }

    protected EntityContext getContext() {
        return context
    }

    @Override
    boolean isLoaded() {
        return context.minecraftEntity?.isAddedToWorld()
    }

    UUID getUUID() {
        return context.minecraftEntity.uniqueID
    }

    @Override
    String getUUIDAsString() {
        return getUUID().toString()
    }

    @Override
    PositionAPI getPosition() {
        return new PositionAPIImpl(context.positionContext)
    }

    @Override
    String getTypeAsString() {
        return null
    }

    @Override
    String getFacingAsString() {
        return null
    }

    @Override
    EntityAPI asAPI(String name) {
        if (name == "immersive_railroading.rolling_stock") {
            def context = new RollingStockContextImpl(this.context as EntityContext<ModdedEntity>)
            return new RollingStockAPIImpl(context)
        }
        if (name == "immersive_railroading.locomotive") {
            def context = new RollingStockContextImpl(this.context as EntityContext<ModdedEntity>)
            return new LocomotiveAPIImpl(context)
        }
        if (name == "immersive_railroading.diesel_locomotive") {
            def context = new RollingStockContextImpl(this.context as EntityContext<ModdedEntity>)
            return new DieselLocomotiveAPIImpl(context)
        }
        throw new NullPointerException()
    }

}
