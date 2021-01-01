package latibro.automation.integration.minecraft

import com.google.common.base.Predicate
import latibro.automation.core.api.APIImpl
import latibro.automation.core.context.ContextProvider
import latibro.automation.core.lua.LuaMethod
import net.minecraft.entity.Entity

class EntityLinker extends APIImpl implements EntityLinkerAPI {

    EntityLinker(ContextProvider contextProvider) {
        super(contextProvider)
    }

    @Override
    EntityLinkAPI fromUUID(UUID uuid) {
        return createEntityLink(uuid)
    }

    @LuaMethod
    @Override
    EntityLinkAPI fromUUID(String uuid) {
        return fromUUID(UUID.fromString(uuid))
    }

    @LuaMethod
    @Override
    EntityLinkAPI[] currentLoaded() {
        List<Entity> entities = getWorld().getEntities(Entity.class, true as Predicate<? super Entity>)
        return entities.collect { createEntityLink(it.getUniqueID()) }
    }

    private EntityLink createEntityLink(UUID uuid) {
        return new EntityLink(getContext(), uuid)
    }

}
