package latibro.automation.core.api

import latibro.automation.api.API
import latibro.automation.core.api.chunk.CoreChunkLinkAPI
import latibro.automation.core.api.entity.CoreEntityLinkAPI
import latibro.automation.core.api.entity.CoreEntityMultiLinkAPI
import latibro.automation.core.api.location.CoreLocationLinkAPI
import latibro.automation.core.api.server.CoreServerLinkAPI
import latibro.automation.core.api.tileentity.CoreTileEntityLinkAPI
import latibro.automation.core.api.tileentity.CoreTileEntityMultiLinkAPI
import latibro.automation.core.api.world.CoreWorldLinkAPI
import latibro.automation.core.context.Context
import latibro.automation.nativeimpl.context.chunk.CoreChunkLinkContext
import latibro.automation.nativeimpl.context.entity.CoreEntityLinkContext
import latibro.automation.nativeimpl.context.entity.multi.CoreEntityMultiLinkContext
import latibro.automation.nativeimpl.context.location.CoreLocationLinkContext
import latibro.automation.nativeimpl.context.server.CoreServerLinkContext
import latibro.automation.nativeimpl.context.tileentity.CoreTileEntityLinkContext
import latibro.automation.nativeimpl.context.tileentity.multi.CoreTileEntityMultiLinkContext
import latibro.automation.nativeimpl.context.world.CoreWorldLinkContext

final class CoreAPIProvider extends AbstractAPIProvider {

    @Override
    API getAPI(Context context) {
        if (context instanceof CoreServerLinkContext) {
            return new CoreServerLinkAPI(context)
        }
        if (context instanceof CoreWorldLinkContext) {
            return new CoreWorldLinkAPI(context)
        }
        if (context instanceof CoreLocationLinkContext) {
            return new CoreLocationLinkAPI(context)
        }
        if (context instanceof CoreEntityLinkContext) {
            return new CoreEntityLinkAPI(context)
        }
        if (context instanceof CoreEntityMultiLinkContext) {
            return new CoreEntityMultiLinkAPI(context)
        }
        if (context instanceof CoreChunkLinkContext) {
            return new CoreChunkLinkAPI(context)
        }
        if (context instanceof CoreTileEntityLinkContext) {
            return new CoreTileEntityLinkAPI(context)
        }
        if (context instanceof CoreTileEntityMultiLinkContext) {
            return new CoreTileEntityMultiLinkAPI(context)
        }
        return super.getAPI(context)
    }

}