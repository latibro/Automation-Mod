package latibro.automation.core.context.link

import latibro.automation.core.api.APIRegistry
import latibro.automation.core.context.Context

abstract class AbstractMultiLinkContext<T extends LinkContext> implements MultiLinkContext<T> {

    @Override
    int count() {
        return asList().size()
    }

    @Override
    MultiLinkContext whereProperty(String property, Object expected) {
        return getFilteredMultiLinkContext({
            def api = APIRegistry.getAPI(it as Context)
            return api[property] == expected
        })
    }

    abstract MultiLinkContext getFilteredMultiLinkContext(Closure filter)

}
