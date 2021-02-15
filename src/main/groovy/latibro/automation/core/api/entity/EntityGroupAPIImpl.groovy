package latibro.automation.core.api.entity

import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.context.Context
import latibro.automation.core.context.entity.EntityContext
import latibro.automation.core.context.entity.group.EntityGroupContext

final class EntityGroupAPIImpl implements EntityGroupAPI, ContextAPI {

    private final EntityGroupContext context

    EntityGroupAPIImpl(EntityGroupContext context) {
        this.context = Objects.requireNonNull(context)
    }

    @Override
    EntityGroupContext getContext() {
        return context
    }

    @Override
    Number size() {
        return context.size()
    }

    @Override
    EntityAPI get(Number index) {
        int adjustedIndex = index - 1 as int
        return apiWrap(context.getAtIndex(adjustedIndex))
    }

    @Override
    EntityAPI first() {
        return apiWrap(context.first())
    }

    @Override
    EntityAPI last() {
        return apiWrap(context.last())
    }

    @Override
    List<EntityAPI> getAll() {
        return apiWrap(context.getAll())
    }

    @Override
    EntityAPI findBy(String property, Object expected) {
        return apiWrap(context.findByProperty(property, expected))
    }

    @Override
    List<EntityAPI> findAllBy(String property, Object expected) {
        return apiWrap(context.findAllByProperty(property, expected))
    }

    @Override
    EntityGroupAPI where(String property, Object expected) {
        return apiWrap(context.wherePropertyIs(property, expected))
    }

    @Override
    List<Object> getAllAsProperty(String property) {
        return context.getAllAsProperty(property).collect {
            if (it instanceof Context) {
                return APIRegistry.getAPI(it)
            }
            return it
        }
    }

    private static EntityGroupAPI apiWrap(EntityGroupContext context) {
        return APIRegistry.getAPI(context) as EntityGroupAPI
    }

    private static EntityAPI apiWrap(EntityContext context) {
        return APIRegistry.getAPI(context) as EntityAPI
    }

    private static List<EntityAPI> apiWrap(List<EntityContext> list) {
        return list.collect { apiWrap(it) }
    }

}
