package latibro.automation.linkbox.redstone

import com.google.gson.Gson
import groovy.transform.CompileStatic
import io.netty.buffer.ByteBuf
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext

import java.nio.charset.Charset

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
        def message = [
                blockPos: blockPos.toLong(),
                powerLevel    : powerLevel
        ]
        def gson = new Gson()
        def json = gson.toJson(message)

        buf.writeCharSequence(json, Charset.forName("UTF-8"))
    }

    @Override
    void fromBytes(ByteBuf buf) {
        def json = buf.readCharSequence(buf.readableBytes(), Charset.forName("UTF-8"))
        def gson = new Gson()
        def message = gson.fromJson(json as String, Map)
        blockPos = BlockPos.fromLong(message.blockPos as long)
        powerLevel = message.powerLevel as int
    }

    static class RedstoneBoxMessageHandler implements IMessageHandler<RedstoneBoxMessage, IMessage> {

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
