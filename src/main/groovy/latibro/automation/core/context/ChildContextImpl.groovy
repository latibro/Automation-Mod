package latibro.automation.core.context

import net.minecraft.world.World

class ChildContextImpl extends ContextImpl implements ChildContext {

    private final Context parent

    ChildContextImpl(Context parent) {
        this(parent, [:])
    }

    ChildContextImpl(Context parent, Map properties) {
        super(properties)
        this.parent = parent
    }

    @Override
    Context getParentContext() {
        return parent
    }

    @Override
    Map getProperties() {
        def map = getParentContext().getProperties()
        map.putAll(super.getProperties())
        return map.asUnmodifiable()
    }

    @Override
    boolean hasProperty(Object key) {
        return super.hasProperty(key) || getParentContext().hasProperty(key)
    }

    @Override
    Object getProperty(Object key) {
        if (super.hasProperty(key)) {
            return super.getProperty(key)
        } else {
            return getParentContext().getProperty(key)
        }
    }

    @Override
    void setProperty(Object key, Object value) {
        if (super.hasProperty(key)) {
            super.setProperty(key, value)
        } else if (getParentContext().hasProperty(key)) {
            getParentContext().setProperty(key, value)
        } else {
            super.setProperty(key, value)
        }
    }

    @Override
    void removeProperty(Object key) {
        if (super.hasProperty(key)) {
            super.removeProperty(key)
        } else if (getParentContext().hasProperty(key)) {
            getParentContext().removeProperty(key)
        } else {
            super.removeProperty(key)
        }
    }

    @Override
    World getWorld() {
        return getParentContext().getWorld()
    }

}
