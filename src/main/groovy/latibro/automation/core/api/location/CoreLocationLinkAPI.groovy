package latibro.automation.core.api.location

import latibro.automation.core.api.CoreAPI
import latibro.automation.core.context.location.LocationContext

final class CoreLocationLinkAPI extends BaseLocationLinkAPI implements CoreAPI {

    CoreLocationLinkAPI(LocationContext context) {
        super(context)
    }

}
