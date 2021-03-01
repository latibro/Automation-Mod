package latibro.automation.core.context.entity


import latibro.automation.core.context.link.SingleLinkContext
import latibro.automation.core.context.location.LocationLinkContext
import latibro.automation.core.context.server.ServerLinkContext
import latibro.automation.core.context.world.WorldLinkContext

interface EntityLinkContext extends SingleLinkContext {

    UUID getUUID()

    boolean isLoaded()

    LocationLinkContext getLocation()

    String getName()

    String getType()

    ServerLinkContext getServer()

    WorldLinkContext getWorld()

}
