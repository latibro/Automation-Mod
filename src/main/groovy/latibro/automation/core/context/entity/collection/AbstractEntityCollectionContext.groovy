package latibro.automation.core.context.entity.collection

import latibro.automation.core.api.entity.EntityCollectionAPI
import latibro.automation.core.api.entity.EntityCollectionAPIImpl
import net.minecraft.entity.Entity

abstract class AbstractEntityCollectionContext<T extends Entity> implements EntityCollectionContext {

    @Override
    EntityCollectionAPI getAPI() {
        return new EntityCollectionAPIImpl(this)
    }

}
