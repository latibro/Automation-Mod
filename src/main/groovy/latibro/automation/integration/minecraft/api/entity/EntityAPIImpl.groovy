package latibro.automation.integration.minecraft.api.entity

import groovy.transform.CompileStatic
import latibro.automation.core.context.entity.AbstractEntityContext
import latibro.automation.core.context.entity.EntityContext
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

}
