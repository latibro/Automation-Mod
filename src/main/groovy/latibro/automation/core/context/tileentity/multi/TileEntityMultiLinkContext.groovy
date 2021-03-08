package latibro.automation.core.context.tileentity.multi


import latibro.automation.core.context.link.MultiLinkContext
import latibro.automation.core.context.server.ServerLinkContext
import latibro.automation.core.context.tileentity.TileEntityLinkContext

interface TileEntityMultiLinkContext extends MultiLinkContext<TileEntityLinkContext> {

    ServerLinkContext getServer()

    TileEntityMultiLinkContext whereProperty(String property, Object expected)

}
