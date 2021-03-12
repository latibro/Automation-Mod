package latibro.automation.core.api.tileentity


import latibro.automation.api.link.tileentity.TileEntityLinkAPI
import latibro.automation.api.link.tileentity.TileEntityMultiLinkAPI
import latibro.automation.core.api.APIRegistry
import latibro.automation.core.api.ContextAPI
import latibro.automation.core.context.tileentity.TileEntityLinkContext
import latibro.automation.core.context.tileentity.multi.TileEntityMultiLinkContext

class BaseTileEntityMultiLinkAPI implements TileEntityMultiLinkAPI, ContextAPI {

    private final TileEntityMultiLinkContext context

    BaseTileEntityMultiLinkAPI(TileEntityMultiLinkContext context) {
        this.context = Objects.requireNonNull(context)
    }

    @Override
    TileEntityMultiLinkContext getContext() {
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
    TileEntityLinkAPI first() {
        return APIRegistry.getAPI(context.asList().first()) as TileEntityLinkAPI
    }

    @Override
    List<TileEntityLinkAPI> asList() {
        def contextList = context.asList()
        def apiList = wrapContextList(contextList)
        return apiList
    }

    @Override
    List<TileEntityLinkAPI> asList(Number maxCount) {
        def contextList = context.asList(maxCount as int)
        def apiList = wrapContextList(contextList)
        return apiList
    }

    @Override
    TileEntityMultiLinkAPI whereProperty(String property, Object expected) {
        //TODO this will need to be filtered on API properties and not on context properties - in some way
        def filteredContext = context.whereProperty(property, expected)
        def api = APIRegistry.getAPI(filteredContext) as TileEntityMultiLinkAPI
        return api
    }

    protected static List<TileEntityLinkAPI> wrapContextList(List<TileEntityLinkContext> contextList) {
        def apiList = contextList.collect {
            APIRegistry.getAPI(it)
        } as List<TileEntityLinkAPI>
        return apiList
    }

}
