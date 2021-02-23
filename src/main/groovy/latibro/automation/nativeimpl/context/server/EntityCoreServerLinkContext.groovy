package latibro.automation.nativeimpl.context.server

import groovy.transform.CompileStatic
import latibro.automation.nativeimpl.context.entity.CoreEntityLinkContext
import net.minecraft.server.MinecraftServer

@CompileStatic
final class EntityCoreServerLinkContext extends CoreServerLinkContext {

    private final CoreEntityLinkContext entity

    EntityCoreServerLinkContext(CoreEntityLinkContext entity) {
        this.entity = Objects.requireNonNull(entity)
    }

    MinecraftServer getNativeServer() {
        return entity.nativeEntity.server
    }

}