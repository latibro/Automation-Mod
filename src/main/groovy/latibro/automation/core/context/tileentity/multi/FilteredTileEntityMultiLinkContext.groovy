package latibro.automation.core.context.tileentity.multi

import latibro.automation.core.LinkType
import latibro.automation.core.context.server.ServerLinkContext
import latibro.automation.core.context.tileentity.TileEntityLinkContext

class FilteredTileEntityMultiLinkContext extends AbstractTileEntityMultiLinkContext {

    private final TileEntityMultiLinkContext parentContext
    private final Closure filter

    FilteredTileEntityMultiLinkContext(TileEntityMultiLinkContext parentContext, Closure filter) {
        this.parentContext = Objects.requireNonNull(parentContext)
        this.filter = filter
    }

    @Override
    boolean isLinked() {
        return parentContext.isLinked()
    }

    @Override
    LinkType getLinkType() {
        return LinkType.DYNAMIC
    }

    @Override
    List<TileEntityLinkContext> asList() {
        return parentContext.asList().findAll(filter)
    }

    @Override
    List<TileEntityLinkContext> asList(int maxCount) {
        int toIndex = Math.min(maxCount, count())
        return parentContext.asList().findAll(filter).subList(0, toIndex)
    }

    @Override
    ServerLinkContext getServer() {
        return parentContext.getServer()
    }

}
