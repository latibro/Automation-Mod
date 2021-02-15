package latibro.automation.core.api.entity

import latibro.automation.AutomationMod
import latibro.automation.core.api.API
import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.api.location.LocationAPI
import latibro.automation.core.context.entity.EntityContext

class BaseEntityAPI<E extends EntityContext> implements EntityAPI, ContextAPI {

    private final E context

    BaseEntityAPI(E context) {
        this.context = Objects.requireNonNull(context)
    }

    @Override
    E getContext() {
        return context
    }

    @Override
    boolean isLoaded() {
        return context.isLoaded()
    }

    @Override
    String getUUID() {
        AutomationMod.logger.debug("uuid {}", context.getUUID())
        return context.getUUID()?.toString()
    }

    @Override
    LocationAPI getLocation() {
        return APIRegistry.getAPI(context.locationContext) as LocationAPI
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
    boolean hasAPI(String name) {
        return APIRegistry.hasAPI(name, this)
    }

    @Override
    API getAPI(String name) {
        return APIRegistry.getAPI(name, this)
    }

}
