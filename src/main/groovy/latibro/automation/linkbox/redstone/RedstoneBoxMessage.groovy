package latibro.automation.linkbox.redstone


import groovy.transform.CompileStatic
import io.netty.buffer.ByteBuf
import net.minecraft.network.PacketBuffer
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext

@CompileStatic
class RedstoneBoxMessage implements IMessage {

    private BlockPos blockPos
    private int powerLevel

    RedstoneBoxMessage() {
    }

    RedstoneBoxMessage(RedstoneBoxTileEntity redstoneBox) {
        this.blockPos = redstoneBox.pos
        this.powerLevel = redstoneBox.powerLevel
    }

    @Override
    void toBytes(ByteBuf buf) {
        def packet = new PacketBuffer(buf)
        packet.writeBlockPos(blockPos)
        packet.writeInt(powerLevel)
    }

    @Override
    void fromBytes(ByteBuf buf) {
        def packet = new PacketBuffer(buf)
        blockPos = packet.readBlockPos()
        powerLevel = packet.readInt()
    }

    static class Handler implements IMessageHandler<RedstoneBoxMessage, IMessage> {

        @Override
        IMessage onMessage(RedstoneBoxMessage message, MessageContext ctx) {
            def player = ctx.serverHandler.player
            def world = player.serverWorld

            world.addScheduledTask(() -> {
                if (world.isBlockLoaded(message.blockPos)) {
                    def te = world.getTileEntity(message.blockPos)
                    if (te instanceof RedstoneBoxTileEntity) {
                        te.setPowerLevel(message.powerLevel)
                    }
                }
            })

            return null
        }

    }
}
