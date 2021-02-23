package latibro.automation.nativeimpl.context.entity.multi

import com.google.common.base.Predicate
import groovy.transform.CompileStatic
import latibro.automation.core.LinkType
import latibro.automation.nativeimpl.context.server.CoreServerLinkContext
import net.minecraft.entity.Entity

import javax.annotation.Nullable

@CompileStatic
final class ServerLoadedCoreEntityMultiLinkContext extends CoreEntityMultiLinkContext {

    private final CoreServerLinkContext server

    ServerLoadedCoreEntityMultiLinkContext(CoreServerLinkContext server) {
        this.server = Objects.requireNonNull(server)
    }

    List<Entity> getNativeEntityList() {
        return (List<Entity>) server.nativeServer.worlds.findResults { it }.collect {
            it.getEntities(Entity.class, new Predicate<Entity>() {
                @Override
                boolean apply(@Nullable Entity input) {
                    return true
                }
            })
        }.flatten()
    }

    @Override
    LinkType getLinkType() {
        return LinkType.DYNAMIC
    }

}
