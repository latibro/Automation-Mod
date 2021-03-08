package latibro.automation.core.api.location

import latibro.automation.api.link.chunk.ChunkLinkAPI
import latibro.automation.api.link.entity.EntityMultiLinkAPI
import latibro.automation.api.link.location.LocationLinkAPI
import latibro.automation.api.link.tileentity.TileEntityMultiLinkAPI
import latibro.automation.api.link.world.WorldLinkAPI
import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.context.location.LocationLinkContext

class BaseLocationLinkAPI implements LocationLinkAPI, ContextAPI {

    private final LocationLinkContext context

    BaseLocationLinkAPI(LocationLinkContext context) {
        this.context = Objects.requireNonNull(context)
    }

    @Override
    LocationLinkContext getContext() {
        return context
    }

    @Override
    Boolean isLinked() {
        return context.isLinked()
    }

    @Override
    String getLinkType() {
        return context.getLinkType()
    }

    @Override
    Boolean isLoaded() {
        return context.isLoaded()
    }

    @Override
    Number getX() {
        return context.x
    }

    @Override
    Number getY() {
        return context.y
    }

    @Override
    Number getZ() {
        return context.z
    }

    @Override
    WorldLinkAPI getWorld() {
        return APIRegistry.getAPI(context.world) as WorldLinkAPI
    }

    @Override
    EntityMultiLinkAPI getEntities() {
        return APIRegistry.getAPI(context.getEntities()) as EntityMultiLinkAPI
    }

    @Override
    EntityMultiLinkAPI getEntities(Boolean includeBoundingBoxes) {
        return APIRegistry.getAPI(context.getEntities(includeBoundingBoxes)) as EntityMultiLinkAPI
    }

    @Override
    TileEntityMultiLinkAPI getTileEntities() {
        return APIRegistry.getAPI(context.getTileEntities()) as TileEntityMultiLinkAPI
    }

    @Override
    Number getDistanceToCoordinates(Number x, Number y, Number z) {
        return context.getDistanceToCoordinates(x as int, y as int, z as int)
    }

    @Override
    ChunkLinkAPI getChunk() {
        return APIRegistry.getAPI(context.getChunk()) as ChunkLinkAPI
    }

}
