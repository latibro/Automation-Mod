package latibro.automation.integration.minecraft

import com.google.common.base.Predicate
import latibro.automation.core.api.APIImpl
import latibro.automation.core.context.ContextProvider
import latibro.automation.core.lua.LuaMethod
import net.minecraft.entity.Entity
import net.minecraft.util.math.BlockPos

class EntityLink extends APIImpl implements EntityLinkAPI {

    //TODO store uuid on the context
    private final UUID uuid

    EntityLink(ContextProvider contextProvider, UUID uuid) {
        super(contextProvider)
        this.uuid = uuid
    }

    @Override
    UUID getUUID() {
        return uuid
    }

    @LuaMethod(name="getUUID")
    @Override
    String getUUIDString() {
        return getUUID().toString()
    }

    @LuaMethod
    @Override
    boolean isLoaded() {
        return findEntity() != null
    }

    @LuaMethod
    String getName() {
        return getEntity().getName()
    }

    @LuaMethod
    String getType() {
        return getEntity().getClass().getName()
    }

    @LuaMethod
    Map getPosition() {
        Entity entity = getEntity()
        BlockPos position = entity.getPosition()
        Map map = new HashMap()
        map.put("x", position.getX())
        map.put("y", position.getY())
        map.put("z", position.getZ())
        return map
    }

    @Override
    Entity getEntity() {
        Entity entity = findEntity()
        if (entity == null) {
            throw new NullPointerException("Entity not loaded")
        }
        return entity
    }

    private Entity findEntity() {
        Predicate p = (Predicate<Entity>) (input) -> input.getUniqueID().equals(getUUID())
        List entities = getWorld().getEntities(Entity.class, p)
        if (entities.isEmpty()) {
            return null
        } else {
            return (Entity) entities.get(0)
        }
    }

}
