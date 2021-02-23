package latibro.automation.core.context.entity.multi

import latibro.automation.core.api.APIRegistry
import latibro.automation.core.context.Context

abstract class AbstractEntityMultiLinkContext implements EntityMultiLinkContext {

    @Override
    int count() {
        return asList().size()
    }

    EntityMultiLinkContext whereProperty(String property, Object expected) {
        return getFilteredEntityMultiLinkContext({
            def api = APIRegistry.getAPI(it as Context)
            return api[property] == expected
        })
    }

    EntityMultiLinkContext getFilteredEntityMultiLinkContext(Closure filter) {
        //TODO could be overridden for more efficient ways to filter closer for core
        return new FilteredEntityMultiLinkContext(this, filter)
    }

}
