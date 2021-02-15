package latibro.automation.core.context.entity.group


import latibro.automation.core.api.APIRegistry
import latibro.automation.core.context.Context
import latibro.automation.core.context.entity.EntityContext

abstract class AbstractEntityGroupContext implements EntityGroupContext {

    @Override
    int size() { //TODO maybe alias size()
        return getAll().size() //TODO maybe abstract for better performance
    }

    @Override
    EntityContext getAtIndex(int index) {
        return getAll().get(index) //TODO maybe abstract for better performance
    }

    @Override
    EntityContext first() {
        return getAtIndex(0)
    }

    @Override
    EntityContext last() {
        return getAtIndex(size()-1)
    }

    @Override
    EntityContext findByProperty(String property, Object expected) {
        return wherePropertyIs(property, expected).first() //TODO could be overridden for more efficient ways to filter closer for core
    }

    @Override
    List<EntityContext> findAllByProperty(String property, Object expected) {
        return wherePropertyIs(property, expected).getAll()
    }

    @Override
    EntityGroupContext wherePropertyIs(String property, Object expected) {
        return getFilteredEntityContext({
            def api = APIRegistry.getAPI(it as Context)
            return api[property] == expected
        })
    }

    EntityGroupContext getFilteredEntityContext(Closure filter) { //TODO could be overridden for more efficient ways to filter closer for core
        return new FilteredEntityGroupContext(this, filter)
    }

    @Override
    EntityGroupContext whereConditionsAre(Map conditions) {
        //TODO implement
        throw new RuntimeException("Not yet implemented")
    }

    @Override
    EntityGroupContext whereAnyConditionsAre(Map conditions) {
        //TODO implement
        throw new RuntimeException("Not yet implemented")
    }

    @Override
    List<Object> getAllAsProperty(String property) {
        return getAll().collect {
            def api = APIRegistry.getAPI(it)
            return api[property]
        }
    }

}
