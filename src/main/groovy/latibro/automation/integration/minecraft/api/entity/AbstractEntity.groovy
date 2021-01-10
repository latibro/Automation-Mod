package latibro.automation.integration.minecraft.api.entity

abstract class AbstractEntity implements Entity {

    private final EntityAPI entityManager

    AbstractEntity(EntityAPI entityManager) {
        this.entityManager = Objects.requireNonNull(entityManager)
    }

    EntityAPI getEntityManager() {
        return entityManager
    }

}
