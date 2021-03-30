package latibro.automation.linkbox.location

import groovy.transform.CompileStatic
import io.netty.buffer.ByteBuf
import net.minecraft.network.PacketBuffer
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext

@CompileStatic
class LocationLinkCardMessage implements IMessage {

    private BlockPos location

    LocationLinkCardMessage() {
    }

    LocationLinkCardMessage(BlockPos location) {
        this.location = location
    }

    @Override
    void toBytes(ByteBuf buf) {
        def packet = new PacketBuffer(buf)
        packet.writeBlockPos(location)
    }

    @Override
    void fromBytes(ByteBuf buf) {
        def packet = new PacketBuffer(buf)
        location = packet.readBlockPos()
    }

    static class Handler implements IMessageHandler<LocationLinkCardMessage, IMessage> {

        @Override
        IMessage onMessage(LocationLinkCardMessage message, MessageContext ctx) {
            def player = ctx.serverHandler.player
            def world = player.serverWorld

            world.addScheduledTask(() -> {
                LocationLinkCardItem.setLocation(player, message.location)
            })

            return null
        }

    }
}
