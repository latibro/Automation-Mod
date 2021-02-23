package latibro.automation.core.context.server

import latibro.automation.core.context.entity.multi.EntityMultiLinkContext
import latibro.automation.core.context.link.SingleLinkContext

interface ServerLinkContext extends SingleLinkContext {

    EntityMultiLinkContext getLoadedEntities()

}