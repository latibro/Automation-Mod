package latibro.automation.core.context.entity

import latibro.automation.core.api.entity.EntityAPI
import latibro.automation.core.api.entity.EntityAPIImpl

abstract class AbstractEntityContext implements EntityContext {

    @Override
    EntityAPI getAPI() {
        return new EntityAPIImpl(this)
    }

}
