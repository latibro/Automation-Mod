package latibro.automation.core.context.position

import latibro.automation.core.api.position.PositionAPI
import latibro.automation.core.api.position.PositionAPIImpl

abstract class AbstractPositionContext implements PositionContext {

    @Override
    PositionAPI getAPI() {
        return new PositionAPIImpl(this)
    }

}
