package latibro.automation.nativeimpl.context.world

import latibro.automation.core.context.CoreContext
import latibro.automation.core.context.server.ServerLinkContext
import latibro.automation.core.context.world.WorldLinkContext
import latibro.automation.nativeimpl.context.entity.multi.CoreEntityMultiLinkContext
import latibro.automation.nativeimpl.context.entity.multi.WorldLoadedCoreEntityMultiLinkContext
import latibro.automation.nativeimpl.context.location.CoreLocationLinkContext
import latibro.automation.nativeimpl.context.location.InstanceCoreLocationLinkContext
import latibro.automation.nativeimpl.context.server.DefaultCoreServerLinkContext
import net.minecraft.world.World

abstract class CoreWorldLinkContext implements WorldLinkContext, CoreContext {

    abstract World getNativeWorld()

    @Override
    boolean isLinked() {
        return nativeWorld
    }

    @Override
    ServerLinkContext getServer() {
        return new DefaultCoreServerLinkContext()
    }

    @Override
    CoreEntityMultiLinkContext getLoadedEntities() {
        return new WorldLoadedCoreEntityMultiLinkContext(this)
    }

    @Override
    CoreLocationLinkContext getLocationByCoordinates(int x, int y, int z) {
        return new InstanceCoreLocationLinkContext(x, y, z, this)
    }

}