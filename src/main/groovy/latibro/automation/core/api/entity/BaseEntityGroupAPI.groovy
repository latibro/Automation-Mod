package latibro.automation.core.api.entity


import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.context.entity.group.EntityGroupContext

class BaseEntityGroupAPI implements EntityGroupAPI, ContextAPI {

    private final EntityGroupContext context

    BaseEntityGroupAPI(EntityGroupContext context) {
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
    List<EntityAPI> asList() {
        def contextList = context.getAll()
        def apiList = contextList.collect {
            APIRegistry.getAPI(it)
        } as List<EntityAPI>
        return apiList
    }

    @Override
    EntityGroupAPI whereProperty(String property, Object expected) {
        //TODO this will need to be filtered on API properties and not on context properties - in some way
        def filteredContext = context.wherePropertyIs(property, expected)
        def api = APIRegistry.getAPI(filteredContext) as EntityGroupAPI
        return api
    }

}
