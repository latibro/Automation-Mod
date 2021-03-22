package latibro.automation.linkbox.data

import com.google.gson.Gson
import groovy.transform.CompileStatic
import io.netty.buffer.ByteBuf
import latibro.automation.AutomationMod
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext

import java.nio.charset.Charset

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
        AutomationMod.logger.info("toBytes")
        def message = [
                blockPos: blockPos.toLong(),
                data: data
        ]
        def gson = new Gson()
        def json = gson.toJson(message)

        buf.writeCharSequence(json, Charset.forName("UTF-8"))
    }

    @Override
    void fromBytes(ByteBuf buf) {
        AutomationMod.logger.info("fromBytes")
        def json = buf.readCharSequence(buf.readableBytes(), Charset.forName("UTF-8"))
        def gson = new Gson()
        def message = gson.fromJson(json as String, Map)
        blockPos = BlockPos.fromLong(message.blockPos as long)
        data = message.data
    }

    static class DataBoxMessageHandler implements IMessageHandler<DataBoxMessage, IMessage> {

        @Override
        IMessage onMessage(DataBoxMessage message, MessageContext ctx) {
            AutomationMod.logger.info("onMessage")
            EntityPlayerMP serverPlayer = ctx.serverHandler.player

            serverPlayer.getServerWorld().addScheduledTask(() -> {
                AutomationMod.logger.info("onMessage task")
                //TODO safe check if TE is loaded and is still DataBoxTE
                def te = serverPlayer.world.getTileEntity(message.blockPos) as DataBoxTileEntity
                te.setData(message.data)
            })

            return null
        }

    }
}
