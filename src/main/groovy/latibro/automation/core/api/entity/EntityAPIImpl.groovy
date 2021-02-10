package latibro.automation.core.api.entity

import latibro.automation.AutomationMod
import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.FeatureAPI
import latibro.automation.core.api.location.LocationAPI
import latibro.automation.core.context.ContextRegistry
import latibro.automation.core.context.entity.EntityContext

class EntityAPIImpl implements EntityAPI {

    private final EntityContext context

    EntityAPIImpl(EntityContext context) {
        this.context = Objects.requireNonNull(context)
    }

    protected EntityContext getContext() {
        return context
    }

    @Override
    boolean isLoaded() {
        return context.isLoaded()
    }

    @Override
    String getUUID() {
        return context.UUID?.toString()
    }

    @Override
    LocationAPI getLocation() {
        return new LocationAPIImpl(context.locationContext)
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
    EntityAPI asType(String name) {
        def subContext = ContextRegistry.getSubContext(name, context)
        return (EntityAPI) APIRegistry.getContextAPI(subContext)
    }

    @Override
    FeatureAPI getAPI(String name) {
        return (FeatureAPI) APIRegistry.getFeatureAPI(name, context)
    }

}
