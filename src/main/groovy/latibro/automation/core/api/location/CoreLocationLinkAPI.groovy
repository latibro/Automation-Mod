package latibro.automation.core.api.location

import latibro.automation.core.api.CoreAPI
import latibro.automation.core.context.location.LocationLinkContext

final class CoreLocationLinkAPI extends BaseLocationLinkAPI implements CoreAPI {

    CoreLocationLinkAPI(LocationLinkContext context) {
        super(context)
    }

}
