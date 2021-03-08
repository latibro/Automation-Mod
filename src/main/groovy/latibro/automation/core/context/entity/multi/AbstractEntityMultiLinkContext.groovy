package latibro.automation.core.context.entity.multi

import latibro.automation.core.context.entity.EntityLinkContext
import latibro.automation.core.context.link.AbstractMultiLinkContext

abstract class AbstractEntityMultiLinkContext extends AbstractMultiLinkContext<EntityLinkContext> implements EntityMultiLinkContext {

    EntityMultiLinkContext getFilteredMultiLinkContext(Closure filter) {
        //TODO could be overridden for more efficient ways to filter closer to core
        return new FilteredEntityMultiLinkContext(this, filter)
    }

    @Override
    EntityMultiLinkContext whereProperty(String property, Object expected) {
        return super.whereProperty(property, expected) as EntityMultiLinkContext
    }

}
