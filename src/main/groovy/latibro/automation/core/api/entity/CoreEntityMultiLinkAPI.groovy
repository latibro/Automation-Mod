package latibro.automation.core.api.entity


import latibro.automation.core.api.CoreAPI
import latibro.automation.core.context.entity.multi.EntityMultiLinkContext

final class CoreEntityMultiLinkAPI extends BaseEntityMultiLinkAPI implements CoreAPI {

    CoreEntityMultiLinkAPI(EntityMultiLinkContext context) {
        super(context)
    }

}
