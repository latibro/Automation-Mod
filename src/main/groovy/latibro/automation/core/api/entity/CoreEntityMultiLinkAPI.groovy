package latibro.automation.core.api.entity


import latibro.automation.core.api.CoreAPI
import latibro.automation.core.context.entity.group.EntityGroupContext

final class CoreEntityMultiLinkAPI extends BaseEntityMultiLinkAPI implements CoreAPI {

    CoreEntityMultiLinkAPI(EntityGroupContext context) {
        super(context)
    }

}
