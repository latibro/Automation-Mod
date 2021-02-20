package latibro.automation.core.api.location

import latibro.automation.core.api.CoreAPI
import latibro.automation.core.context.location.LocationContext

final class CoreLocationAPI extends BaseLocationAPI implements CoreAPI {

    CoreLocationAPI(LocationContext context) {
        super(context)
    }

}
