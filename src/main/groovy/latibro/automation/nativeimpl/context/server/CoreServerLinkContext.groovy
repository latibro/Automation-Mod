package latibro.automation.nativeimpl.context.server

import latibro.automation.core.LinkType
import latibro.automation.core.context.server.ServerLinkContext
import latibro.automation.nativeimpl.context.entity.multi.CoreEntityMultiLinkContext
import latibro.automation.nativeimpl.context.entity.multi.ServerLoadedCoreEntityMultiLinkContext
import net.minecraft.server.MinecraftServer

abstract class CoreServerLinkContext implements ServerLinkContext {

    abstract MinecraftServer getNativeServer()

    @Override
    boolean isLinked() {
        return nativeServer
    }

    @Override
    LinkType getLinkType() {
        return LinkType.STATIC
    }

    @Override
    CoreEntityMultiLinkContext getLoadedEntities() {
        return new ServerLoadedCoreEntityMultiLinkContext(this)
    }

}