package latibro.automation.nativeimpl.context.entity.multi


import groovy.transform.CompileStatic
import latibro.automation.core.LinkType
import latibro.automation.nativeimpl.context.server.CoreServerLinkContext
import net.minecraft.entity.Entity

@CompileStatic
final class ServerLoadedCoreEntityMultiLinkContext extends CoreEntityMultiLinkContext {

    private final CoreServerLinkContext server

    ServerLoadedCoreEntityMultiLinkContext(CoreServerLinkContext server) {
        this.server = Objects.requireNonNull(server)
    }

    List<Entity> getNativeEntityList() {
        return (List<Entity>) server.nativeServer.worlds.findResults { it }.collect {
            def loadedEntityList = it.@loadedEntityList
            return loadedEntityList
        }.flatten()
    }

    @Override
    LinkType getLinkType() {
        return LinkType.DYNAMIC
    }

}
