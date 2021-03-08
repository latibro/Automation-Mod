package latibro.automation.core.context.tileentity.multi

import latibro.automation.core.context.link.AbstractMultiLinkContext
import latibro.automation.core.context.tileentity.TileEntityLinkContext

abstract class AbstractTileEntityMultiLinkContext extends AbstractMultiLinkContext<TileEntityLinkContext> implements TileEntityMultiLinkContext {

    TileEntityMultiLinkContext getFilteredMultiLinkContext(Closure filter) {
        //TODO could be overridden for more efficient ways to filter closer to core
        return new FilteredTileEntityMultiLinkContext(this, filter)
    }

    @Override
    TileEntityMultiLinkContext whereProperty(String property, Object expected) {
        return super.whereProperty(property, expected) as TileEntityMultiLinkContext
    }

}
