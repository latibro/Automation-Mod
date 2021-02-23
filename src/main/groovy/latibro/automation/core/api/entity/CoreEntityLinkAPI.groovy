package latibro.automation.core.api.entity

import latibro.automation.core.api.CoreAPI
import latibro.automation.core.context.entity.EntityLinkContext

final class CoreEntityLinkAPI extends BaseEntityLinkAPI implements CoreAPI {

    CoreEntityLinkAPI(EntityLinkContext context) {
        super(context)
    }

}
