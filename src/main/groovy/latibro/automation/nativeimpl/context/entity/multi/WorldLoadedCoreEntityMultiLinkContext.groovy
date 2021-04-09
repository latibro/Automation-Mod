package latibro.automation.nativeimpl.context.entity.multi

import groovy.transform.CompileStatic
import latibro.automation.core.LinkType
import latibro.automation.nativeimpl.context.world.CoreWorldLinkContext
import net.minecraft.entity.Entity

@CompileStatic
final class WorldLoadedCoreEntityMultiLinkContext extends CoreEntityMultiLinkContext {

    private final CoreWorldLinkContext world

    WorldLoadedCoreEntityMultiLinkContext(CoreWorldLinkContext world) {
        this.world = Objects.requireNonNull(world)
    }

    List<Entity> getNativeEntityList() {
        def loadedEntityList = world.nativeWorld.@loadedEntityList
        return loadedEntityList
    }

    @Override
    LinkType getLinkType() {
        return LinkType.DYNAMIC
    }

}
