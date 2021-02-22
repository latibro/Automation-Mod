package latibro.automation.core.api.entity

import latibro.automation.core.api.CoreAPI
import latibro.automation.core.context.entity.EntityContext

final class CoreEntityLinkAPI extends BaseEntityLinkAPI implements CoreAPI {

    CoreEntityLinkAPI(EntityContext context) {
        super(context)
    }

}
