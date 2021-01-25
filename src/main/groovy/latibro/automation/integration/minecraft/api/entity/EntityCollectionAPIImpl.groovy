package latibro.automation.integration.minecraft.api.entity

import groovy.transform.CompileStatic
import latibro.automation.core.context.entity.AbstractEntityCollectionContext
import latibro.automation.core.context.entity.EntityCollectionContext
import net.minecraft.entity.Entity

@CompileStatic
class EntityCollectionAPIImpl implements EntityCollectionAPI {

    private final EntityCollectionContext<Entity> context

    EntityCollectionAPIImpl(EntityCollectionContext context) {
        this.context = Objects.requireNonNull(context)
    }

    EntityCollectionAPIImpl(Collection minecraftEntityCollection) {
        this(new AbstractEntityCollectionContext() {
            @Override
            Collection getAllMinecraftEntity() {
                return minecraftEntityCollection
            }
        })
    }

    EntityAPI getByUUIDAsString(String uuid) {
        return getByUUID(UUID.fromString(uuid))
    }

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
        return context.getAllMinecraftEntity().collect { new EntityAPIImpl(it) } as Collection<EntityAPI>
    }

    @Override
    Collection<String> getAllAsUUIDAsString() {
        return context.getAllMinecraftEntity().collect { it.uniqueID.toString() } as Collection<String>
    }

    @Override
    int size() {
        return context.getAllMinecraftEntity().size()
    }

    @Override
    EntityAPI getAt(int index) {
        return new EntityAPIImpl(context.getAllMinecraftEntity().getAt(index))
    }

}
