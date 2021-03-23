package latibro.automation.linkbox.data

import groovy.transform.CompileStatic
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
        return data
    }

    void setData(String data) {
        this.data = data

        if (world.isRemote) {
            def message = new DataBoxMessage(this)
            NetworkProxy.sendMessageToServer(message)
        } else {
            markDirty()

            IBlockState state = world.getBlockState(pos)
            world.notifyBlockUpdate(pos, state, state, 3)
        }
    }

    @Override
    NBTTagCompound getUpdateTag() {
        def nbt = new NBTTagCompound()
        writeToNBT(nbt)
        return nbt
    }

    @Override
    SPacketUpdateTileEntity getUpdatePacket() {
        def nbt = getUpdateTag()
        def packet = new SPacketUpdateTileEntity(pos, getBlockMetadata(), nbt)
        return packet
    }

    @Override
    void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        def nbt = packet.getNbtCompound()
        readFromNBT(nbt)
    }

    @Override
    void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt)
        def automationNbt = nbt.getCompoundTag("automation")
        data = automationNbt?.getString("data")
    }

    @Override
    NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt)
        def automationNbt = nbt.getCompoundTag("automation") ?: new NBTTagCompound()
        automationNbt.setString("data", data ?: "")
        nbt.setTag("automation", automationNbt)
        return nbt
    }

}
