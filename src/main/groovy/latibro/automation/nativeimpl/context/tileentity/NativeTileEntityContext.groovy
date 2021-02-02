package latibro.automation.nativeimpl.context.tileentity

import latibro.automation.core.context.tileentity.TileEntityContext
import latibro.automation.nativeimpl.context.NativeContext
import latibro.automation.nativeimpl.context.position.NativePositionContext
import latibro.automation.nativeimpl.context.world.NativeWorldContext
import net.minecraft.tileentity.TileEntity

interface NativeTileEntityContext extends TileEntityContext, NativeContext {

    TileEntity getNativeTileEntity()

    @Override
    NativePositionContext getPositionContext()

    @Override
    NativeWorldContext getWorldContext()

}
