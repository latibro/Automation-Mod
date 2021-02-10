package latibro.automation.core.context.entity.group

import latibro.automation.core.context.CoreContext
import latibro.automation.core.context.entity.EntityContext
import latibro.automation.core.context.server.ServerContext

final class FilteredEntityGroupContext extends AbstractEntityGroupContext implements CoreContext {

    private final EntityGroupContext parentContext
    private final Closure filter

    FilteredEntityGroupContext(EntityGroupContext parentContext, Closure filter) {
        this.parentContext = Objects.requireNonNull(parentContext)
        this.filter = filter
    }

    @Override
    List<EntityContext> getAll() {
        return parentContext.getAll().findAll(filter)
    }

    @Override
    ServerContext getServerContext() {
        return parentContext.getServerContext()
    }

}
