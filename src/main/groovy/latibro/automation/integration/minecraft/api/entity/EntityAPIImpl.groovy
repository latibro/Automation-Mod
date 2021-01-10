package latibro.automation.integration.minecraft.api.entity

import latibro.automation.core.api.APIHost
import latibro.automation.core.api.AbstractHostedAPI

import javax.annotation.Nonnull

class EntityAPIImpl extends AbstractHostedAPI implements EntityAPI {

    EntityAPIImpl(@Nonnull APIHost host) {
        super(host)
    }

    @Override
    @Nonnull
    LinkedEntity getLinkedEntityFromUUID(@Nonnull UUID uuid) {
        return new LinkedEntityImpl(this, uuid)
    }

    @Override
    @Nonnull
    DirectEntity getDirectEntityByUUID(@Nonnull String uuid) {
        return getDirectEntityByUUID(UUID.fromString(uuid))
    }

    @Override
    @Nonnull
    DirectEntity getDirectEntityByUUID(@Nonnull UUID uuid) {
        Objects.requireNonNull(uuid)
        def entities = host.minecraftWorld.loadedEntityList.findAll { it.getUniqueID() == uuid }
        if (entities.isEmpty()) {
            throw new RuntimeException("Entity is not available") //TODO better exception
        } else if (entities.size() > 1) {
            throw new RuntimeException("Multiple entity are available") //TODO better exception
        }
        return new DirectEntityImpl(this, entities.first())
    }

}
