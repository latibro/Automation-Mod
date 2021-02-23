package latibro.automation.nativeimpl.context.entity

import groovy.transform.CompileStatic
import latibro.automation.core.LinkType
import latibro.automation.nativeimpl.context.server.CoreServerLinkContext
import net.minecraft.entity.Entity

@CompileStatic
final class UUIDCoreEntityLinkContext extends CoreEntityLinkContext {

    private final UUID uuid
    private final CoreServerLinkContext server

    UUIDCoreEntityLinkContext(UUID uuid, CoreServerLinkContext server) {
        this.uuid = Objects.requireNonNull(uuid)
        this.server = Objects.requireNonNull(server)
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
