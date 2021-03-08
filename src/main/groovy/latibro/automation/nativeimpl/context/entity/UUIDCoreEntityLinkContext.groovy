package latibro.automation.nativeimpl.context.entity

import groovy.transform.CompileStatic
import latibro.automation.core.LinkType
import latibro.automation.nativeimpl.context.server.CoreServerLinkContext
import net.minecraft.entity.Entity

@CompileStatic
final class UUIDCoreEntityLinkContext extends CoreEntityLinkContext {

    private final UUID uuid

    UUIDCoreEntityLinkContext(UUID uuid) {
        this.uuid = Objects.requireNonNull(uuid)
    }

    Entity getNativeEntity() {
        return server.nativeServer.getEntityFromUuid(uuid)
    }

    @Override
    LinkType getLinkType() {
        return LinkType.STATIC
    }

    @Override
    CoreServerLinkContext getServer() {
        return server
    }

    @Override
    UUID getUUID() {
        return uuid
    }

}
