package latibro.automation.nativeimpl.context.world


import latibro.automation.nativeimpl.context.entity.collection.NativeEntityCollectionContext
import latibro.automation.nativeimpl.context.entity.collection.NativeWorldLoadedEntityCollectionContext
import latibro.automation.nativeimpl.context.position.NativePositionContext
import latibro.automation.nativeimpl.context.position.NativeStaticPositionContext
import net.minecraft.world.World

abstract class AbstractNativeWorldContext implements NativeWorldContext {

    abstract World getNativeWorld()

    @Override
    NativeEntityCollectionContext getLoadedEntityCollectionContext() {
        return new NativeWorldLoadedEntityCollectionContext(this)
    }

    @Override
    NativePositionContext getPositionContextByCoordinate(int x, int y, int z) {
        return new NativeStaticPositionContext(x, y, z, this)
    }

}