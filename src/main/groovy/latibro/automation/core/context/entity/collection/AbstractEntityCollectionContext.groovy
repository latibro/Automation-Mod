package latibro.automation.core.context.entity.collection

import latibro.automation.core.context.entity.EntityContext

abstract class AbstractEntityCollectionContext implements EntityCollectionContext {

    @Override
    int size() {
        return all.size()
    }

    @Override
    EntityContext getAt(int index) {
        return all[index]
    }

    @Override
    EntityCollectionContext filterByName(String name) {
        return new FilteredEntityCollectionContext(this, { it.name == name })
    }

}
