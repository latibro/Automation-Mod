package latibro.automation.integration.minecraft.api.entity

class EntityWrapper implements Entity {

    @Delegate private final Entity entity

    EntityWrapper(Entity entity) {
        this.entity = Objects.requireNonNull(entity)
    }

    protected Entity getEntity() {
        return entity
    }

}
