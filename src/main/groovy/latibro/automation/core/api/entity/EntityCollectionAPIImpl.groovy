package latibro.automation.core.api.entity


import latibro.automation.core.context.entity.collection.EntityCollectionContext

class EntityCollectionAPIImpl implements EntityCollectionAPI {

    private final EntityCollectionContext context

    EntityCollectionAPIImpl(EntityCollectionContext context) {
        this.context = Objects.requireNonNull(context)
    }

    @Override
    Collection<EntityAPI> getAll() {
        return context.getAll().collect { it.getAPI() }
    }

    @Override
    double size() {
        return context.size() as double
    }

    @Override
    EntityAPI getAt(double index) {
        return context.getAt(index as int).getAPI()
    }

    @Override
    Collection<String> getAllAsUUID() {
        return getAll().collect { it.UUID } as Collection<String>
    }

    EntityAPI getByUUID(String uuid) {
        def matches = getAll().findAll { it.UUID == uuid }
        if (!matches) {
            throw new NullPointerException("No matches")
        }
        if (matches.size() > 1) {
            throw new RuntimeException("Multiple matches")
        }
        return matches.first()
    }

}
