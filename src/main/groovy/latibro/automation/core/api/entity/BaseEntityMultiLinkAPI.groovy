package latibro.automation.core.api.entity

import latibro.automation.api.link.entity.EntityMultiLinkAPI
import latibro.automation.api.link.entity.EntityLinkAPI
import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.context.entity.group.EntityGroupContext

class BaseEntityMultiLinkAPI implements EntityMultiLinkAPI, ContextAPI {

    private final EntityGroupContext context

    BaseEntityMultiLinkAPI(EntityGroupContext context) {
        this.context = Objects.requireNonNull(context)
    }

    @Override
    EntityGroupContext getContext() {
        return context
    }

    @Override
    Boolean isLinked() {
        //TODO implement
        throw new RuntimeException("Not yet implemented")
    }

    @Override
    String getLinkType() {
        //TODO implement
        throw new RuntimeException("Not yet implemented")
    }

    @Override
    Number count() {
        return context.size()
    }

    @Override
    List<EntityLinkAPI> asList() {
        def contextList = context.getAll()
        def apiList = contextList.collect {
            APIRegistry.getAPI(it)
        } as List<EntityLinkAPI>
        return apiList
    }

    @Override
    EntityMultiLinkAPI filterByProperty(String property, Object expected) {
        //TODO this will need to be filtered on API properties and not on context properties - in some way
        def filteredContext = context.wherePropertyIs(property, expected)
        def api = APIRegistry.getAPI(filteredContext) as EntityMultiLinkAPI
        return api
    }

}
