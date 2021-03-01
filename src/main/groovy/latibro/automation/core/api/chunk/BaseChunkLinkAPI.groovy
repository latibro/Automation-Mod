package latibro.automation.core.api.chunk

import latibro.automation.api.link.chunk.ChunkLinkAPI
import latibro.automation.api.link.world.WorldLinkAPI
import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.context.chunk.ChunkLinkContext

class BaseChunkLinkAPI implements ChunkLinkAPI, ContextAPI {

    private final ChunkLinkContext context

    BaseChunkLinkAPI(ChunkLinkContext context) {
        this.context = Objects.requireNonNull(context)
    }

    @Override
    ChunkLinkContext getContext() {
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
        return context.getX()
    }

    @Override
    Number getZ() {
        return context.getZ()
    }

    @Override
    WorldLinkAPI getWorld() {
        return APIRegistry.getAPI(context.world) as WorldLinkAPI
    }

    @Override
    Boolean isForceLoaded() {
        return context.isForceLoaded()
    }

    @Override
    void forceLoad() {
        context.forceLoad()
    }

    @Override
    void unforceLoad() {
        context.unforceLoad()
    }

}
