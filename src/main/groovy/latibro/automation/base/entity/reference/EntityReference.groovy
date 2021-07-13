package latibro.automation.base.entity.reference

import latibro.automation.core.source.reference.SourceReference
import net.minecraft.entity.Entity

class EntityReference implements SourceReference {

    private final UUID entityUuid

    private EntityReference(UUID entityUuid) {
        this.entityUuid = entityUuid
    }

    UUID getEntityUuid() {
        return entityUuid
    }

    @Override
    Map asMap() {
        return [
                "uuid": entityUuid
        ]
    }

    static EntityReference create(UUID entityUuid) {
        return new EntityReference(entityUuid)
    }

    static EntityReference create(String entityUuid) {
        def uuid = UUID.fromString(entityUuid)
        return create(uuid)
    }

    static EntityReference create(Entity entity) {
        create(entity.uniqueID)
    }

}
