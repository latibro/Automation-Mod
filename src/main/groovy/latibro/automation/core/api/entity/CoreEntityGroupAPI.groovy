package latibro.automation.core.api.entity


import latibro.automation.core.api.CoreAPI
import latibro.automation.core.context.entity.group.EntityGroupContext

final class CoreEntityGroupAPI extends BaseEntityGroupAPI implements CoreAPI {

    CoreEntityGroupAPI(EntityGroupContext context) {
        super(context)
    }

}
