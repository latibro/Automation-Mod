package latibro.automation.integration.minecraft.api.entity


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
class EntityAPIImpl<T extends Entity> implements EntityAPI {

    private final EntityContext context

    EntityAPIImpl(EntityContext context) {
        this.context = Objects.requireNonNull(context) as EntityContext
    }

    EntityAPIImpl(Entity minecraftEntity) {
        this(new AbstractEntityContext() {
            @Override
            Entity getMinecraftEntity() {
                return minecraftEntity
            }
        })
    }

    protected EntityContext<T> getContext() {
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
        if (name == "minecraft.living_entity") {
            return new LivingEntityAPIImpl(context)
        }
        throw new NullPointerException()
    }

}
