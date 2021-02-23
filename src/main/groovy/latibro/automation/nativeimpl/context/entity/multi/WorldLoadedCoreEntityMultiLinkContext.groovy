package latibro.automation.nativeimpl.context.entity.multi

import com.google.common.base.Predicate
import groovy.transform.CompileStatic
import latibro.automation.core.LinkType
import latibro.automation.nativeimpl.context.world.CoreWorldLinkContext
import net.minecraft.entity.Entity

import javax.annotation.Nullable

@CompileStatic
final class WorldLoadedCoreEntityMultiLinkContext extends CoreEntityMultiLinkContext {

    private final CoreWorldLinkContext world

    WorldLoadedCoreEntityMultiLinkContext(CoreWorldLinkContext world) {
        this.world = Objects.requireNonNull(world)
    }

    List<Entity> getNativeEntityList() {
        return world.nativeWorld.getEntities(Entity.class, new Predicate<Entity>() {
            @Override
            boolean apply(@Nullable Entity input) {
                return true
            }
        })
    }

    @Override
    LinkType getLinkType() {
        return LinkType.DYNAMIC
    }

}
