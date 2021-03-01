package latibro.automation.nativeimpl.context.chunk


import groovy.transform.CompileStatic
import latibro.automation.core.LinkType
import latibro.automation.nativeimpl.context.location.CoreLocationLinkContext
import latibro.automation.nativeimpl.context.world.CoreWorldLinkContext
import net.minecraft.util.math.ChunkPos

@CompileStatic
final class LocationCoreChunkLinkContext extends CoreChunkLinkContext {

    private final CoreLocationLinkContext location

    LocationCoreChunkLinkContext(CoreLocationLinkContext location) {
        this.location = Objects.requireNonNull(location)
    }

    @Override
    ChunkPos getNativeChunk() {
        return new ChunkPos(location.nativeLocation)
    }

    @Override
    CoreWorldLinkContext getWorld() {
        return location.getWorld()
    }

    @Override
    boolean isLinked() {
        return nativeChunk
    }

    @Override
    LinkType getLinkType() {
        return LinkType.DYNAMIC
    }

}
