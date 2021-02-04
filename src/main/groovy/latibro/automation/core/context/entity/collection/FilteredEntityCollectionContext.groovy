package latibro.automation.core.context.entity.collection

import latibro.automation.core.context.CoreContext
import latibro.automation.core.context.entity.EntityContext
import latibro.automation.core.context.server.ServerContext

final class FilteredEntityCollectionContext extends AbstractEntityCollectionContext implements CoreContext {

    private final EntityCollectionContext parentContext
    private final Closure filter

    FilteredEntityCollectionContext(EntityCollectionContext parentContext, Closure filter) {
        this.parentContext = Objects.requireNonNull(parentContext)
        this.filter = filter
    }

    @Override
    Collection<EntityContext> getAll() {
        return parentContext.all.findAll(filter)
    }

    @Override
    ServerContext getServerContext() {
        return parentContext.getServerContext()
    }

}
