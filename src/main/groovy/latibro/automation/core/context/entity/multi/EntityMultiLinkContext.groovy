package latibro.automation.core.context.entity.multi

import latibro.automation.core.context.entity.EntityLinkContext
import latibro.automation.core.context.link.MultiLinkContext
import latibro.automation.core.context.server.ServerLinkContext

interface EntityMultiLinkContext extends MultiLinkContext<EntityLinkContext> {

    ServerLinkContext getServer()

    EntityMultiLinkContext whereProperty(String property, Object expected)

}
