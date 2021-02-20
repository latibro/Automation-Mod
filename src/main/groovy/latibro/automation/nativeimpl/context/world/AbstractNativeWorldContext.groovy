package latibro.automation.nativeimpl.context.world


import latibro.automation.nativeimpl.context.entity.group.NativeEntityGroupContext
import latibro.automation.nativeimpl.context.entity.group.NativeWorldLoadedEntityGroupContext
import latibro.automation.nativeimpl.context.location.NativeLocationContext
import latibro.automation.nativeimpl.context.location.NativeStaticLocationContext
import net.minecraft.world.World

abstract class AbstractNativeWorldContext implements NativeWorldContext {

    abstract World getNativeWorld()

    @Override
    NativeEntityGroupContext getLoadedEntitiesContext() {
        return new NativeWorldLoadedEntityGroupContext(this)
    }

    @Override
    NativeLocationContext getLocationContextByCoordinates(int x, int y, int z) {
        return new NativeStaticLocationContext(x, y, z, this)
    }

}