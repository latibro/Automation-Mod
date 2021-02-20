package latibro.automation.core.api.world


import latibro.automation.core.api.CoreAPI
import latibro.automation.core.context.world.WorldContext

final class CoreWorldAPI extends BaseWorldAPI implements CoreAPI {

    CoreWorldAPI(WorldContext context) {
        super(context)
    }

}
