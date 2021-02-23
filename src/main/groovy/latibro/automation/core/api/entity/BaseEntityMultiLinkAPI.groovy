package latibro.automation.core.api.entity

import latibro.automation.api.link.entity.EntityLinkAPI
import latibro.automation.api.link.entity.EntityMultiLinkAPI
import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.context.entity.multi.EntityMultiLinkContext

class BaseEntityMultiLinkAPI implements EntityMultiLinkAPI, ContextAPI {

    private final EntityMultiLinkContext context

    BaseEntityMultiLinkAPI(EntityMultiLinkContext context) {
        this.context = Objects.requireNonNull(context)
    }

    @Override
    EntityMultiLinkContext getContext() {
        return context
    }

    @Override
    Boolean isLinked() {
        return context.isLinked()
    }

    @Override
    String getLinkType() {
        return context.getLinkType()
    }

    @Override
    Number count() {
        return context.count()
    }

    @Override
    List<EntityLinkAPI> asList() {
        def contextList = context.asList()
        def apiList = contextList.collect {
            APIRegistry.getAPI(it)
        } as List<EntityLinkAPI>
        return apiList
    }

    @Override
    EntityMultiLinkAPI whereProperty(String property, Object expected) {
        //TODO this will need to be filtered on API properties and not on context properties - in some way
        def filteredContext = context.whereProperty(property, expected)
        def api = APIRegistry.getAPI(filteredContext) as EntityMultiLinkAPI
        return api
    }

}
