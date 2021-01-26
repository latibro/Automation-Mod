package latibro.automation.nativeimpl.context.tileentity

import latibro.automation.core.context.tileentity.TileEntityContext
import latibro.automation.nativeimpl.context.NativeContext
import net.minecraft.tileentity.TileEntity

interface NativeTileEntityContext extends TileEntityContext, NativeContext {

    TileEntity getNativeTileEntity()

}
