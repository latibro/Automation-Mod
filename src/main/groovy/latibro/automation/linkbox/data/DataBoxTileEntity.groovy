package latibro.automation.linkbox.data

import groovy.transform.CompileStatic
import latibro.automation.AutomationMod
import latibro.automation.core.peripheral.PeripheralTileEntity
import latibro.automation.nativeimpl.context.tileentity.InstanceCoreTileEntityLinkContext
import latibro.automation.proxy.NetworkProxy
import net.minecraft.block.state.IBlockState
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.NetworkManager
import net.minecraft.network.play.server.SPacketUpdateTileEntity

@CompileStatic
class DataBoxTileEntity extends PeripheralTileEntity {

    private String data

    @Override
    String getComponentName() {
        return "data_box"
    }

    @Override
    protected DataBoxAPI getPeripheralAPI() {
        def tileEntityContext = new InstanceCoreTileEntityLinkContext(this)
        def dataBoxAPI = new DataBoxAPIImpl(tileEntityContext)
        return dataBoxAPI
    }

    String getData() {
        AutomationMod.logger.info("getData {}", world.isRemote)

        //def nbt = getTileData().getCompoundTag("automation")
        //def data = nbt?.getString("data")

        AutomationMod.logger.info("getData {} {}", data)
        return data
    }

    void setData(String data) {
        AutomationMod.logger.info("setData a {} {}", data, world.isRemote)

        //def nbt = getTileData().getCompoundTag("automation")?: new NBTTagCompound()
        //nbt.setString("data", data)
        //getTileData().setTag("automation", nbt)

        this.data = data

        if (world.isRemote) {
            AutomationMod.logger.info("setData is remote {}", data)
            def message = new DataBoxMessage(this)
            NetworkProxy.sendMessageToServer(message)
            AutomationMod.logger.info("sendMessageToServer {}", message)
        } else {
            AutomationMod.logger.info("setData not remote {}", data)
            markDirty()

            AutomationMod.logger.info("setData b {} {}", data, getUpdateTag())
            IBlockState state = world.getBlockState(pos)
            world.notifyBlockUpdate(pos, state, state, 3)
            AutomationMod.logger.info("notifyBlockUpdate {} {}", pos, state)
        }

        AutomationMod.logger.info("setData c {} {}", data, getUpdateTag())
    }

    @Override
    NBTTagCompound getUpdateTag() {
        AutomationMod.logger.info("getUpdateTag a")
        def nbt = new NBTTagCompound()
        writeToNBT(nbt)
        AutomationMod.logger.info("getUpdateTag b {}", nbt)
        return nbt
    }

    @Override
    SPacketUpdateTileEntity getUpdatePacket() {
        AutomationMod.logger.info("getUpdatePacket a")
        def nbt = getUpdateTag()
        def packet = new SPacketUpdateTileEntity(pos, getBlockMetadata(), nbt)
        AutomationMod.logger.info("getUpdatePacket b {} {}", nbt, packet)
        return packet
    }

    @Override
    void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        AutomationMod.logger.info("onDataPacket a {}", packet)
        def nbt = packet.getNbtCompound()
        readFromNBT(nbt)
        AutomationMod.logger.info("onDataPacket b {} {}", nbt, packet)
    }

    @Override
    void handleUpdateTag(NBTTagCompound tag) {
        AutomationMod.logger.info("handleUpdateTag a {}", tag)
        super.handleUpdateTag(tag)
        AutomationMod.logger.info("handleUpdateTag b {}", tag)
    }

    @Override
    void readFromNBT(NBTTagCompound nbt) {
        AutomationMod.logger.info("readFromNBT a {} {}", nbt, data)
        super.readFromNBT(nbt)
        def automationNbt = nbt.getCompoundTag("automation")
        data = automationNbt?.getString("data")
        AutomationMod.logger.info("readFromNBT b {} {}", nbt, data)
    }

    @Override
    NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        AutomationMod.logger.info("writeToNBT a {} {}", nbt, data)
        super.writeToNBT(nbt)
        def automationNbt = nbt.getCompoundTag("automation") ?: new NBTTagCompound()
        automationNbt.setString("data", data ?: "")
        nbt.setTag("automation", automationNbt)
        AutomationMod.logger.info("writeToNBT b {} {}", nbt, data)
        return nbt
    }

}
