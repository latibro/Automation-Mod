package latibro.automation.integration.minecraft.api.entity

import latibro.automation.core.context.entity.AbstractEntityCollectionContext
import latibro.automation.core.context.entity.EntityCollectionContext
import net.minecraft.entity.Entity

class EntityCollectionAPIImpl implements EntityCollectionAPI {

    private final EntityCollectionContext<Entity> context

    EntityCollectionAPIImpl(EntityCollectionContext context) {
        this.context = Objects.requireNonNull(context)
    }

    EntityCollectionAPIImpl(Collection<Entity> minecraftEntityCollection) {
        this(new AbstractEntityCollectionContext() {
            @Override
            Collection getAllMinecraftEntity() {
                return minecraftEntityCollection
            }
        })
    }

    @Override
    EntityAPI getByUUID(UUID uuid) {
        def matches = context.getAllMinecraftEntity().findAll { it.uniqueID == uuid }
        if (!matches) {
            throw new NullPointerException("No matches")
        }
        if (matches.size() > 1) {
            throw new RuntimeException("Multiple matches")
        }
        return new EntityAPIImpl(matches.first())
    }

    @Override
    Collection<EntityAPI> getAll() {
        return context.getAllMinecraftEntity().collect { new EntityAPIImpl(it) }
    }

}
