package latibro.automation.core.api.entity

import latibro.automation.AutomationMod
import latibro.automation.api.API
import latibro.automation.api.APIProviderAPI
import latibro.automation.api.link.entity.EntityLinkAPI
import latibro.automation.api.link.location.LocationLinkAPI
import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.context.entity.EntityLinkContext

class BaseEntityLinkAPI<E extends EntityLinkContext> implements EntityLinkAPI, APIProviderAPI, ContextAPI {

    private final E context

    BaseEntityLinkAPI(E context) {
        this.context = Objects.requireNonNull(context)
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
    E getContext() {
        return context
    }

    @Override
    Boolean isLoaded() {
        return context.isLoaded()
    }

    @Override
    String getUUID() {
        AutomationMod.logger.debug("uuid {}", context.getUUID())
        return context.getUUID()?.toString()
    }

    @Override
    LocationLinkAPI getLocation() {
        return APIRegistry.getAPI(context.location) as LocationLinkAPI
    }

    @Override
    String getName() {
        return context.getName()
    }

    @Override
    String getType() {
        return context.getType()
    }

    @Override
    List<String> getAPINames() {
        return APIRegistry.getAPINames(this)
    }

    @Override
    Boolean hasAPI(String name) {
        return APIRegistry.hasAPI(name, this)
    }

    @Override
    API getAPI(String name) {
        return APIRegistry.getAPI(name, this)
    }

}
