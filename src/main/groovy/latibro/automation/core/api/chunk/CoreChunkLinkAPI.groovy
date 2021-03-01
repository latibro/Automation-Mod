package latibro.automation.core.api.chunk

import latibro.automation.core.api.CoreAPI
import latibro.automation.core.context.chunk.ChunkLinkContext

final class CoreChunkLinkAPI extends BaseChunkLinkAPI implements CoreAPI {

    CoreChunkLinkAPI(ChunkLinkContext context) {
        super(context)
    }

}
