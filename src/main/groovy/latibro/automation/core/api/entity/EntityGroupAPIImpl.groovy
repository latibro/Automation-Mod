package latibro.automation.core.api.entity

import latibro.automation.core.api.APIRegistry
import latibro.automation.core.context.Context
import latibro.automation.core.context.entity.EntityContext
import latibro.automation.core.context.entity.group.EntityGroupContext

final class EntityGroupAPIImpl implements EntityGroupAPI {

    private final EntityGroupContext context

    EntityGroupAPIImpl(EntityGroupContext context) {
        this.context = Objects.requireNonNull(context)
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
    EntityGroupAPI where(Map conditions) {
        return apiWrap(context.whereConditionsAre(conditions))
    }

    @Override
    EntityGroupAPI whereAny(Map conditions) {
        return apiWrap(context.whereAnyConditionsAre(conditions))
    }

    @Override
    List<Object> getAllAsProperty(String property) {
        return context.getAllAsProperty(property).collect {
            if (it instanceof Context) {
                return APIRegistry.getContextAPI(it)
            }
            return it
        }
    }

    private static EntityGroupAPI apiWrap(EntityGroupContext context) {
        return (EntityGroupAPI) APIRegistry.getContextAPI(context)
    }

    private static EntityAPI apiWrap(EntityContext context) {
        return (EntityAPI) APIRegistry.getContextAPI(context)
    }

    private static List<EntityAPI> apiWrap(List<EntityContext> list) {
        return list.collect { apiWrap(it) }
    }

}
