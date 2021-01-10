package latibro.automation.integration.minecraft.api.entity

import latibro.automation.integration.minecraft.api.position.Position

class LinkedEntityImpl extends AbstractEntity implements LinkedEntity {

    private final UUID entityUUID;

    LinkedEntityImpl(EntityAPI entityManager, UUID entityUUID) {
        super(entityManager)
        this.entityUUID = Objects.requireNonNull(entityUUID)
    }

    @Override
    net.minecraft.entity.Entity getMinecraftEntity() {
        return asDirectEntity().getMinecraftEntity()
    }

    @Override
    UUID getUUID() {
        return entityUUID
    }

    @Override
    String getType() {
        return asDirectEntity().getType()
    }

    @Override
    Position getPosition() {
        return asDirectEntity().getPosition()
    }

    @Override
    boolean isAvailable() {
        try {
            return asDirectEntity().isAvailable()
        } catch (Exception ignored) {
            return false
        }
    }

    @Override
    DirectEntity asDirectEntity() {
        return entityManager.getByUUID(entityUUID)
    }

}
