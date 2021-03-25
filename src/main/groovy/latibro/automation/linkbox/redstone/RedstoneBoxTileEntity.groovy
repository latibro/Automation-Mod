package latibro.automation.linkbox.redstone

import groovy.transform.CompileStatic
import latibro.automation.core.peripheral.PeripheralTileEntity
import latibro.automation.nativeimpl.context.tileentity.InstanceCoreTileEntityLinkContext
import latibro.automation.proxy.NetworkProxy
import net.minecraft.block.state.IBlockState
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.NetworkManager
import net.minecraft.network.play.server.SPacketUpdateTileEntity

@CompileStatic
class RedstoneBoxTileEntity extends PeripheralTileEntity {

    private int powerLevel

    @Override
    String getComponentName() {
        return "redstone_box"
    }

    @Override
    protected RedstoneBoxAPI getPeripheralAPI() {
        def tileEntityContext = new InstanceCoreTileEntityLinkContext(this)
        def api = new RedstoneBoxAPIImpl(tileEntityContext)
        return api
    }

    int getPowerLevel() {
        return powerLevel
    }

    void setPowerLevel(int level) {
        assert (0..15).contains(level)

        this.powerLevel = level

        if (world.isRemote) {
            def message = new RedstoneBoxMessage(this)
            NetworkProxy.sendMessageToServer(message)
        } else {
            markDirty()

            IBlockState state = world.getBlockState(pos)
            world.notifyBlockUpdate(pos, state, state, 3)
            world.notifyNeighborsOfStateChange(pos, this.blockType, false)
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
        powerLevel = automationNbt?.getInteger("powerLevel")
    }

    @Override
    NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt)
        def automationNbt = nbt.getCompoundTag("automation") ?: new NBTTagCompound()
        automationNbt.setInteger("powerLevel", powerLevel ?: 0)
        nbt.setTag("automation", automationNbt)
        return nbt
    }

}
