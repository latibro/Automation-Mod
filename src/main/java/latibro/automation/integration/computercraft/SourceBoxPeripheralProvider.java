package latibro.automation.integration.computercraft;

import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import latibro.automation.source.SourceBoxTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SourceBoxPeripheralProvider implements IPeripheralProvider {

    @Nullable
    @Override
    public IPeripheral getPeripheral(@Nonnull World world, @Nonnull BlockPos blockPos, @Nonnull EnumFacing enumFacing) {
        if (world.getTileEntity(blockPos) instanceof SourceBoxTileEntity) {
            SourceBoxTileEntity te = (SourceBoxTileEntity) world.getTileEntity(blockPos);
            return new SourceBoxPeripheral(te);
        } else {
            return null;
        }
    }

}
