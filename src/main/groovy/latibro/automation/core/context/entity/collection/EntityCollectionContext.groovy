package latibro.automation.core.context.entity.collection

import latibro.automation.core.api.entity.EntityCollectionAPI
import latibro.automation.core.context.Context
import latibro.automation.core.context.entity.EntityContext
import latibro.automation.core.context.server.ServerContextProvider

interface EntityCollectionContext extends Context<EntityCollectionAPI>, ServerContextProvider {

    int size()

    Collection<EntityContext> getAll()

    EntityContext getAt(int index)

}
