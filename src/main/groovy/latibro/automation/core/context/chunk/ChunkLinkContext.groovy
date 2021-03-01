package latibro.automation.core.context.chunk

import latibro.automation.core.context.link.SingleLinkContext
import latibro.automation.core.context.world.WorldLinkContext

interface ChunkLinkContext extends SingleLinkContext {

    boolean isLoaded()

    int getX()

    int getZ()

    WorldLinkContext getWorld()

    boolean isForceLoaded()

    void forceLoad()

    void unforceLoad()

}