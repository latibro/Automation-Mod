package latibro.automation.core.api.entity

import latibro.automation.core.api.CoreAPI
import latibro.automation.core.context.entity.EntityContext

final class CoreEntityAPI extends BaseEntityAPI implements CoreAPI {

    CoreEntityAPI(EntityContext context) {
        super(context)
    }

}
