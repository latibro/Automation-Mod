package latibro.automation.nativeimpl.context.tileentity.multi

import groovy.transform.CompileStatic
import latibro.automation.core.LinkType
import latibro.automation.nativeimpl.context.location.CoreLocationLinkContext
import net.minecraft.tileentity.TileEntity

@CompileStatic
final class LocationCoreTileEntityMultiLinkContext extends CoreTileEntityMultiLinkContext {

    private final CoreLocationLinkContext location

    LocationCoreTileEntityMultiLinkContext(CoreLocationLinkContext location) {
        this.location = Objects.requireNonNull(location)
    }

    List<TileEntity> getNativeTileEntityList() {
        def loadedTileEntityList = location.world.nativeWorld.@loadedTileEntityList.findAll {
            //TODO find a way to use bounding box like entity
            return it.getPos() == location.nativeLocation
        }
        return loadedTileEntityList
    }

    @Override
    LinkType getLinkType() {
        return LinkType.DYNAMIC
    }

}
