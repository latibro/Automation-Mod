package latibro.automation.integration.minecraft.api.entity

import latibro.automation.integration.minecraft.api.position.Position

class LinkedEntityImpl extends AbstractEntity implements LinkedEntity {

    private final UUID entityUUID;

    LinkedEntityImpl(EntityAPI host, UUID entityUUID) {
        super(host)
        this.entityUUID = Objects.requireNonNull(entityUUID)
    }

    @Override
    net.minecraft.entity.Entity getMinecraftEntity() {
        return asLoadedEntity().getMinecraftEntity()
    }

    @Override
    UUID getUUID() {
        return entityUUID
    }

    @Override
    String getType() {
        return asLoadedEntity().getType()
    }

    @Override
    Position getPosition() {
        return asLoadedEntity().getPosition()
    }

    @Override
    boolean isAvailable() {
        try {
            return asLoadedEntity().isAvailable()
        } catch (Exception ignored) {
            return false
        }
    }

    @Override
    Entity asLoadedEntity() {
        return host.getByUUID(entityUUID)
    }

}
