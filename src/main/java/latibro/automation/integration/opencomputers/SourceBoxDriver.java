package latibro.automation.integration.opencomputers;

import latibro.automation.source.SourceBoxTileEntity;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverSidedTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SourceBoxDriver extends DriverSidedTileEntity {

    @Override
    public ManagedEnvironment createEnvironment(World world, BlockPos blockPos, EnumFacing enumFacing) {
        if (worksWith(world, blockPos, enumFacing)) {
            SourceBoxTileEntity computerRelayBox = (SourceBoxTileEntity) world.getTileEntity(blockPos);
            return new SourceBoxEnvironment(computerRelayBox);
        } else {
            return null;
        }
    }

    @Override
    public Class<?> getTileEntityClass() {
        return SourceBoxTileEntity.class;
    }

}
