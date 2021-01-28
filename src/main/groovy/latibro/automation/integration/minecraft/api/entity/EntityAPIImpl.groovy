package latibro.automation.integration.minecraft.api.entity

import latibro.automation.integration.minecraft.context.entity.EntityContext

class EntityAPIImpl extends latibro.automation.core.api.entity.EntityAPIImpl implements EntityAPI {

    EntityAPIImpl(EntityContext context) {
        super(context)
    }

    EntityContext getContext() {
        return super.context as EntityContext
    }

}
