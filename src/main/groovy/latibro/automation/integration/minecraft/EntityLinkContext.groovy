package latibro.automation.integration.minecraft

import latibro.automation.core.context.ChildContextImpl
import latibro.automation.core.context.Context

public class EntityLinkContext extends ChildContextImpl {

    public EntityLinkContext(Context parent) {
        super(parent);
    }

    public EntityLinkContext(Context parent, Map properties) {
        super(parent, properties);
    }

}
