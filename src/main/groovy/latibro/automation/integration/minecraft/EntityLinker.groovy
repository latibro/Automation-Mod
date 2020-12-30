package latibro.automation.integration.minecraft;

import com.google.common.base.Predicate;
import latibro.automation.core.api.APIImpl;
import latibro.automation.core.context.ContextProvider;
import latibro.automation.core.lua.LuaMethod;
import net.minecraft.entity.Entity;

import java.util.List;
import java.util.UUID;

public class EntityLinker extends APIImpl implements EntityLinkerAPI {

    public EntityLinker(ContextProvider contextProvider) {
        super(contextProvider);
    }

    @Override
    public EntityLinkAPI fromUUID(UUID uuid) {
        return createEntityLink(uuid);
    }

    @LuaMethod
    @Override
    public EntityLinkAPI fromUUID(String uuid) {
        return fromUUID(UUID.fromString(uuid));
    }

    @LuaMethod
    @Override
    public EntityLinkAPI[] currentLoaded() {
        Predicate p = (Predicate<Entity>) input -> true;
        List<Entity> entities = getWorld().getEntities(Entity.class, p);
        return entities.stream().map(entity -> createEntityLink(entity.getUniqueID())).toArray(EntityLinkAPI[]::new);
    }

    private EntityLink createEntityLink(UUID uuid) {
        return new EntityLink(getContext(), uuid);
    }

}
