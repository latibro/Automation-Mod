package latibro.automation.core.context.entity.group

import latibro.automation.core.context.Context
import latibro.automation.core.context.entity.EntityContext
import latibro.automation.core.context.server.ServerContextProvider

interface EntityGroupContext extends Context, ServerContextProvider {

    int size()

    EntityContext getAtIndex(int index)
    EntityContext first()
    EntityContext last()
    List<EntityContext> getAll()

    EntityContext findByProperty(String property, Object expected)
    List<EntityContext> findAllByProperty(String property, Object expected)

    EntityGroupContext wherePropertyIs(String property, Object expected)

    // "and" conditions
    EntityGroupContext whereConditionsAre(Map conditions)
    // "or" conditions
    EntityGroupContext whereAnyConditionsAre(Map conditions)

    List<Object> getAllAsProperty(String property)

}
