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
        return context.getAll().collect {
            APIRegistry.getAPI(it)
        } as List<EntityAPI>
    }

    @Override
    EntityGroupAPI whereProperty(String property, Object expected) {
        //TODO this will need to be filtered on API properties and not on context properties - in some way
        return APIRegistry.getAPI(context.wherePropertyIs(property, expected)) as EntityGroupAPI
    }

}
