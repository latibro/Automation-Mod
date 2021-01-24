package latibro.automation.core.context.tileentity

import latibro.automation.core.context.Context
import latibro.automation.core.context.position.PositionContextProvider
import latibro.automation.core.context.world.WorldContextProvider
import net.minecraft.tileentity.TileEntity

interface TileEntityContext<T extends TileEntity> extends Context, WorldContextProvider, PositionContextProvider {

    T getMinecraftTileEntity()

}