package latibro.automation.linkbox.entity

import groovy.transform.CompileStatic
import io.netty.buffer.ByteBuf
import net.minecraft.network.PacketBuffer
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext

@CompileStatic
class EntityLinkCardMessage implements IMessage {

    private UUID entityUUID

    EntityLinkCardMessage() {
    }

    EntityLinkCardMessage(UUID entityUUID) {
        this.entityUUID = entityUUID
    }

    @Override
    void toBytes(ByteBuf buf) {
        def packet = new PacketBuffer(buf)
        packet.writeUniqueId(entityUUID)
    }

    @Override
    void fromBytes(ByteBuf buf) {
        def packet = new PacketBuffer(buf)
        entityUUID = packet.readUniqueId()
    }

    static class Handler implements IMessageHandler<EntityLinkCardMessage, IMessage> {

        @Override
        IMessage onMessage(EntityLinkCardMessage message, MessageContext ctx) {
            def player = ctx.serverHandler.player
            def world = player.serverWorld

            world.addScheduledTask(() -> {
                EntityLinkCardItem.setEntityUUID(player, message.entityUUID)
            })

            return null
        }

    }
}
