package latibro.automation.integration.minecraft.api.entity

import groovy.transform.CompileStatic
import latibro.automation.integration.minecraft.api.position.Position
import latibro.automation.integration.minecraft.api.position.PositionImpl

@CompileStatic
class DirectEntityImpl extends AbstractEntity implements DirectEntity {

    private final net.minecraft.entity.Entity minecraftEntity

    DirectEntityImpl(EntityAPI entityManager, net.minecraft.entity.Entity minecraftEntity) {
        super(entityManager)
        this.minecraftEntity = Objects.requireNonNull(minecraftEntity)
    }

    @Override
    net.minecraft.entity.Entity getMinecraftEntity() {
        return minecraftEntity
    }

    @Override
    UUID getUUID() {
        return minecraftEntity.uniqueID
    }

    @Override
    String getType() {
        return minecraftEntity.name
    }

    @Override
    Position getPosition() {
        def blockPos = minecraftEntity.position
        return new PositionImpl(blockPos.x, blockPos.y, blockPos.z)
    }

    @Override
    boolean isAvailable() {
        return minecraftEntity.isAddedToWorld() //TODO maybe also isDead() and more
    }

}
