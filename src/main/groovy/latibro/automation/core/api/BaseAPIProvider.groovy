package latibro.automation.core.api

import latibro.automation.api.API
import latibro.automation.core.api.chunk.BaseChunkLinkAPI
import latibro.automation.core.api.entity.BaseEntityLinkAPI
import latibro.automation.core.api.entity.BaseEntityMultiLinkAPI
import latibro.automation.core.api.location.BaseLocationLinkAPI
import latibro.automation.core.api.server.BaseServerLinkAPI
import latibro.automation.core.api.tileentity.BaseTileEntityLinkAPI
import latibro.automation.core.api.tileentity.BaseTileEntityMultiLinkAPI
import latibro.automation.core.api.world.BaseWorldLinkAPI
import latibro.automation.core.context.Context
import latibro.automation.core.context.chunk.ChunkLinkContext
import latibro.automation.core.context.entity.EntityLinkContext
import latibro.automation.core.context.entity.multi.EntityMultiLinkContext
import latibro.automation.core.context.location.LocationLinkContext
import latibro.automation.core.context.server.ServerLinkContext
import latibro.automation.core.context.tileentity.TileEntityLinkContext
import latibro.automation.core.context.tileentity.multi.TileEntityMultiLinkContext
import latibro.automation.core.context.world.WorldLinkContext

class BaseAPIProvider extends AbstractAPIProvider {

    @Override
    API getAPI(Context context) {
        if (context instanceof ServerLinkContext) {
            return new BaseServerLinkAPI(context)
        }
        if (context instanceof WorldLinkContext) {
            return new BaseWorldLinkAPI(context)
        }
        if (context instanceof LocationLinkContext) {
            return new BaseLocationLinkAPI(context)
        }
        if (context instanceof EntityLinkContext) {
            return new BaseEntityLinkAPI(context)
        }
        if (context instanceof EntityMultiLinkContext) {
            return new BaseEntityMultiLinkAPI(context)
        }
        if (context instanceof ChunkLinkContext) {
            return new BaseChunkLinkAPI(context)
        }
        if (context instanceof TileEntityLinkContext) {
            return new BaseTileEntityLinkAPI(context)
        }
        if (context instanceof TileEntityMultiLinkContext) {
            return new BaseTileEntityMultiLinkAPI(context)
        }
        return super.getAPI(context)
    }

}