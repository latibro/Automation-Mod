package latibro.automation.integration.minecraft.api.entity

import groovy.transform.CompileStatic
import latibro.automation.core.api.APIHost
import latibro.automation.core.api.AbstractHostedAPI

import javax.annotation.Nonnull

@CompileStatic
class EntityAPIImpl extends AbstractHostedAPI implements EntityAPI {

    EntityAPIImpl(@Nonnull APIHost host) {
        super(host)
    }

    @Override
    List<String> getAllLoadedAsUUIDString() {
        return getAllLoadedAsUUID().collect { it.toString() }
    }

    @Override
    List<UUID> getAllLoadedAsUUID() {
        return getAllLoaded().collect { it.getUUID() }
    }

    @Override
    List<DirectEntity> getAllLoaded() {
        return host.minecraftWorld.loadedEntityList.collect { getByUUID(it.uniqueID) }
    }

    @Override
    @Nonnull
    DirectEntity getByUUIDString(@Nonnull String uuid) {
        return getByUUID(UUID.fromString(uuid))
    }

    @Override
    @Nonnull
    DirectEntity getByUUID(@Nonnull UUID uuid) {
        Objects.requireNonNull(uuid)
        def entities = host.minecraftWorld.loadedEntityList.findAll { it.uniqueID == uuid }
        if (entities.isEmpty()) {
            throw new RuntimeException("Entity is not available") //TODO better exception
        } else if (entities.size() > 1) {
            throw new RuntimeException("Multiple entity are available") //TODO better exception
        }
        return new DirectEntityImpl(this, entities.first())
    }

    @Override
    @Nonnull
    LinkedEntity linkByUUID(@Nonnull UUID uuid) {
        return new LinkedEntityImpl(this, uuid)
    }

}
