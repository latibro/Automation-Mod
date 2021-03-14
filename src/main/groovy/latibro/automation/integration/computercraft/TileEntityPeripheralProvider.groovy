package latibro.automation.integration.computercraft

import dan200.computercraft.api.ComputerCraftAPI
import dan200.computercraft.api.peripheral.IPeripheral
import dan200.computercraft.api.peripheral.IPeripheralProvider
import groovy.transform.CompileStatic
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

import javax.annotation.Nonnull
import javax.annotation.Nullable

@CompileStatic
class TileEntityPeripheralProvider implements IPeripheralProvider {

    static void init() {
        ComputerCraftAPI.registerPeripheralProvider(new TileEntityPeripheralProvider())
    }

    @Nullable
    @Override
    IPeripheral getPeripheral(@Nonnull World world, @Nonnull BlockPos blockPos, @Nonnull EnumFacing enumFacing) {
        if (world.getTileEntity(blockPos) instanceof IPeripheral) {
            return (IPeripheral) world.getTileEntity(blockPos)
        } else {
            return null
        }
    }

}
