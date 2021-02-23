package latibro.automation.nativeimpl.context.world

import latibro.automation.core.context.CoreContext
import latibro.automation.core.context.world.WorldLinkContext
import latibro.automation.nativeimpl.context.entity.multi.CoreEntityMultiLinkContext
import latibro.automation.nativeimpl.context.entity.multi.WorldLoadedCoreEntityMultiLinkContext
import latibro.automation.nativeimpl.context.location.CoreLocationContext
import latibro.automation.nativeimpl.context.location.InstanceCoreLocationContext
import net.minecraft.world.World

abstract class CoreWorldLinkContext implements WorldLinkContext, CoreContext {

    abstract World getNativeWorld()

    @Override
    boolean isLinked() {
        return nativeWorld
    }

    @Override
    CoreEntityMultiLinkContext getLoadedEntities() {
        return new WorldLoadedCoreEntityMultiLinkContext(this)
    }

    @Override
    CoreLocationContext getLocationByCoordinates(int x, int y, int z) {
        return new InstanceCoreLocationContext(x, y, z, this)
    }

}