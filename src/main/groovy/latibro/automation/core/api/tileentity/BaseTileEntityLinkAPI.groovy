package latibro.automation.core.api.tileentity


import latibro.automation.api.API
import latibro.automation.api.APIProviderAPI
import latibro.automation.api.link.location.LocationLinkAPI
import latibro.automation.api.link.tileentity.TileEntityLinkAPI
import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.context.tileentity.TileEntityLinkContext

class BaseTileEntityLinkAPI<E extends TileEntityLinkContext> implements TileEntityLinkAPI, APIProviderAPI, ContextAPI {

    private final E context

    BaseTileEntityLinkAPI(E context) {
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
    LocationLinkAPI getLocation() {
        return APIRegistry.getAPI(context.location) as LocationLinkAPI
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
