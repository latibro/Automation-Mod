package latibro.automation.integration.minecraft

import latibro.automation.core.context.ChildContextImpl
import latibro.automation.core.context.Context

class EntityLinkContext extends ChildContextImpl {

    EntityLinkContext(Context parent) {
        super(parent)
    }

    EntityLinkContext(Context parent, Map properties) {
        super(parent, properties)
    }

}
