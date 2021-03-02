package latibro.automation.nativeimpl.context.chunk

import groovy.transform.CompileStatic
import latibro.automation.core.LinkType
import latibro.automation.nativeimpl.context.world.CoreWorldLinkContext
import net.minecraft.util.math.ChunkPos
import net.minecraftforge.common.ForgeChunkManager

@CompileStatic
final class InstanceCoreChunkLinkContext extends CoreChunkLinkContext {

    private final ChunkPos nativeChunk
    private final CoreWorldLinkContext world

    InstanceCoreChunkLinkContext(ChunkPos chunk, CoreWorldLinkContext world) {
        nativeChunk = Objects.requireNonNull(chunk)
        this.world = Objects.requireNonNull(world)
    }

    InstanceCoreChunkLinkContext(int x, int z, CoreWorldLinkContext world) {
        this(new ChunkPos(Objects.requireNonNull(x), Objects.requireNonNull(z)), world)
    }

    @Override
    ChunkPos getNativeChunk() {
        return nativeChunk
    }

    @Override
    CoreWorldLinkContext getWorld() {
        return world
    }

    @Override
    LinkType getLinkType() {
        return LinkType.STATIC
    }

    protected Set<ForgeChunkManager.Ticket> findExistingTickets() {
        def tickets = super.findExistingTickets()
        tickets.findAll {
            it.type == ForgeChunkManager.Type.NORMAL
        }
        return tickets
    }

}
