package latibro.automation.nativeimpl.context.chunk

import groovy.transform.CompileStatic
import latibro.automation.AutomationMod
import latibro.automation.core.LinkType
import latibro.automation.nativeimpl.context.entity.CoreEntityLinkContext
import latibro.automation.nativeimpl.context.world.CoreWorldLinkContext
import net.minecraft.util.math.ChunkPos
import net.minecraftforge.common.ForgeChunkManager
import net.minecraftforge.event.entity.EntityEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@CompileStatic
final class EntityCoreChunkLinkContext extends CoreChunkLinkContext {

    private final CoreEntityLinkContext entity

    EntityCoreChunkLinkContext(CoreEntityLinkContext entity) {
        this.entity = Objects.requireNonNull(entity)
    }

    @Override
    ChunkPos getNativeChunk() {
        return new ChunkPos(entity.location.nativeLocation)
    }

    @Override
    CoreWorldLinkContext getWorld() {
        return entity.getWorld()
    }

    @Override
    LinkType getLinkType() {
        return LinkType.DYNAMIC
    }

    protected ForgeChunkManager.Ticket createTicket() {
        def ticket = ForgeChunkManager.requestTicket(AutomationMod.instance, world.nativeWorld, ForgeChunkManager.Type.ENTITY)
        ticket.bindEntity(entity.nativeEntity)
        return ticket
    }

    protected Set<ForgeChunkManager.Ticket> findExistingTickets() {
        def tickets = super.findExistingTickets()
        tickets.findAll {
            it.type == ForgeChunkManager.Type.ENTITY &&
                    it.entity == entity.nativeEntity
        }
        return tickets
    }

    @SubscribeEvent
    static void handleChunkChangeEvent(EntityEvent.EnteringChunk event) {
        def oldChunkPos = new ChunkPos(event.oldChunkX, event.oldChunkZ)
        def newChunkPos = new ChunkPos(event.newChunkX, event.newChunkZ)
        def ticket = ForgeChunkManager.getPersistentChunksFor(event.entity.world).get(oldChunkPos).find { ticket ->
            ticket.modId == AutomationMod.MODID &&
                    !ticket.isPlayerTicket() &&
                    ticket.type == ForgeChunkManager.Type.ENTITY &&
                    ticket.entity == event.entity
        }
        if (ticket) {
            AutomationMod.logger.info("Entity forced chunk load/unload {} {} {}", event.getEntity(), oldChunkPos, newChunkPos)
            ForgeChunkManager.forceChunk(ticket, newChunkPos)
            ForgeChunkManager.unforceChunk(ticket, oldChunkPos)
        }
    }

}
