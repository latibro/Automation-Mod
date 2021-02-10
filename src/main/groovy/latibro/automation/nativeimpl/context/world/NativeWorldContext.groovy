package latibro.automation.nativeimpl.context.world


import latibro.automation.core.context.world.WorldContext
import latibro.automation.nativeimpl.context.NativeContext
import latibro.automation.nativeimpl.context.entity.group.NativeEntityGroupContext
import latibro.automation.nativeimpl.context.location.NativeLocationContext
import latibro.automation.nativeimpl.context.server.NativeServerContext
import net.minecraft.world.World

interface NativeWorldContext extends WorldContext, NativeContext {

    World getNativeWorld()

    @Override
    NativeLocationContext getLocationContextByCoordinate(int x, int y, int z)

    @Override
    NativeEntityGroupContext getLoadedEntitiesContext()

    @Override
    NativeServerContext getServerContext()

}