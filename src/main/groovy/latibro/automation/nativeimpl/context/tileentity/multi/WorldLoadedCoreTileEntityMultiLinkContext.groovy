package latibro.automation.nativeimpl.context.tileentity.multi


import groovy.transform.CompileStatic
import latibro.automation.AutomationMod
import latibro.automation.core.LinkType
import latibro.automation.nativeimpl.context.world.CoreWorldLinkContext
import net.minecraft.tileentity.TileEntity

@CompileStatic
final class WorldLoadedCoreTileEntityMultiLinkContext extends CoreTileEntityMultiLinkContext {

    private final CoreWorldLinkContext world

    WorldLoadedCoreTileEntityMultiLinkContext(CoreWorldLinkContext world) {
        this.world = Objects.requireNonNull(world)
    }

    List<TileEntity> getNativeTileEntityList() {
        def loadedTileEntityList = world.nativeWorld.@loadedTileEntityList
        AutomationMod.logger.info("### loadedTileEntityList {}", loadedTileEntityList)
        return loadedTileEntityList
    }

    @Override
    LinkType getLinkType() {
        return LinkType.DYNAMIC
    }

}
