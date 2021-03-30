package latibro.automation.linkbox.data


import groovy.transform.CompileStatic
import io.netty.buffer.ByteBuf
import net.minecraft.network.PacketBuffer
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext

@CompileStatic
class DataBoxMessage implements IMessage {

    private BlockPos blockPos
    private String data

    DataBoxMessage() {
    }

    DataBoxMessage(DataBoxTileEntity dataBox) {
        this.blockPos = dataBox.pos
        this.data = dataBox.data
    }

    @Override
    void toBytes(ByteBuf buf) {
        def packet = new PacketBuffer(buf)
        packet.writeBlockPos(blockPos)
        packet.writeString(data)
    }

    @Override
    void fromBytes(ByteBuf buf) {
        def packet = new PacketBuffer(buf)
        blockPos = packet.readBlockPos()
        data = packet.readString(4096)
    }

    static class Handler implements IMessageHandler<DataBoxMessage, IMessage> {

        @Override
        IMessage onMessage(DataBoxMessage message, MessageContext ctx) {
            def player = ctx.serverHandler.player
            def world = player.serverWorld

            world.addScheduledTask(() -> {
                if (world.isBlockLoaded(message.blockPos)) {
                    def te = world.getTileEntity(message.blockPos)
                    if (te instanceof DataBoxTileEntity) {
                        te.setData(message.data)
                    }
                }
            })

            return null
        }

    }
}
