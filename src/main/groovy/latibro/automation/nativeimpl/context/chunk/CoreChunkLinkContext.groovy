package latibro.automation.nativeimpl.context.chunk

import groovy.transform.CompileStatic
import latibro.automation.AutomationMod
import latibro.automation.core.context.CoreContext
import latibro.automation.core.context.chunk.ChunkLinkContext
import latibro.automation.nativeimpl.context.world.CoreWorldLinkContext
import net.minecraft.util.math.ChunkPos
import net.minecraftforge.common.ForgeChunkManager

@CompileStatic
abstract class CoreChunkLinkContext implements ChunkLinkContext, CoreContext {

    abstract ChunkPos getNativeChunk()

    @Override
    boolean isLoaded() {
        return world.nativeWorld.isBlockLoaded(nativeChunk.getBlock(0, 0, 0))
    }

    @Override
    abstract CoreWorldLinkContext getWorld()

    private ForgeChunkManager.Ticket findExistingTicket() {
        def tickets = ForgeChunkManager.getPersistentChunksFor(world.nativeWorld).get(nativeChunk).findAll { ticket ->
            ticket.modId == AutomationMod.MODID
        }
        if (tickets.size() > 1) {
            throw new RuntimeException("Multiple chunk tickets found")
        }
        if (tickets.size() < 1) {
            return null
        }
        return tickets.first()
    }

    boolean isForceLoaded() {
        def ticket = findExistingTicket()
        return ticket != null
    }

    void forceLoad() {
        def ticket = findExistingTicket()
        if (ticket) {
            AutomationMod.logger.info("Chunk already force loaded")
            return
        }
        ticket = ForgeChunkManager.requestTicket(AutomationMod.instance, world.nativeWorld, ForgeChunkManager.Type.NORMAL)
        ForgeChunkManager.forceChunk(ticket, nativeChunk)
    }

    void unforceLoad() {
        def ticket = findExistingTicket()
        if (!ticket) {
            AutomationMod.logger.info("Chunk not force loaded")
            return
        }
        ForgeChunkManager.unforceChunk(ticket, nativeChunk)
    }

}
