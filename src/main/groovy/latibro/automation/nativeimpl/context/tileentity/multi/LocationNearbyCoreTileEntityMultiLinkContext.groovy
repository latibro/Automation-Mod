package latibro.automation.nativeimpl.context.tileentity.multi

import groovy.transform.CompileStatic
import latibro.automation.core.LinkType
import latibro.automation.nativeimpl.context.location.CoreLocationLinkContext
import net.minecraft.tileentity.TileEntity

@CompileStatic
final class LocationNearbyCoreTileEntityMultiLinkContext extends CoreTileEntityMultiLinkContext {

    private final CoreLocationLinkContext location
    private final double range

    LocationNearbyCoreTileEntityMultiLinkContext(CoreLocationLinkContext location, double range) {
        this.location = Objects.requireNonNull(location)
        this.range = range
    }

    List<TileEntity> getNativeTileEntityList() {
        def loadedTileEntityList = location.world.nativeWorld.@loadedTileEntityList.findAll {
            //TODO find a way to use bounding box like entity
            return location.nativeLocation.getDistance(it.pos.x, it.pos.y, it.pos.z) <= range
        }
        return loadedTileEntityList
    }

    @Override
    LinkType getLinkType() {
        return LinkType.DYNAMIC
    }

}
