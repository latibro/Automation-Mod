package latibro.automation.core.context

import net.minecraft.world.World

public class ChildContextImpl extends ContextImpl implements ChildContext {

    private final Context parent;

    public ChildContextImpl(Context parent) {
        this(parent, null);
    }

    public ChildContextImpl(Context parent, Map properties) {
        super(properties);
        this.parent = parent;
    }

    @Override
    public Context getParentContext() {
        return parent;
    }

    @Override
    public Map getProperties() {
        Map map = getParentContext().getProperties();
        map.putAll(super.getProperties());
        return Collections.unmodifiableMap(map);
    }

    @Override
    public boolean hasProperty(Object key) {
        return super.hasProperty(key) || getParentContext().hasProperty(key);
    }

    @Override
    public Object getProperty(Object key) {
        if (super.hasProperty(key)) {
            return super.getProperty(key);
        } else {
            return getParentContext().getProperty(key);
        }
    }

    @Override
    public void setProperty(Object key, Object value) {
        if (super.hasProperty(key)) {
            super.setProperty(key, value);
        } else if (getParentContext().hasProperty(key)) {
            getParentContext().setProperty(key, value);
        } else {
            super.setProperty(key, value);
        }
    }

    @Override
    public void removeProperty(Object key) {
        if (super.hasProperty(key)) {
            super.removeProperty(key);
        } else if (getParentContext().hasProperty(key)) {
            getParentContext().removeProperty(key);
        } else {
            super.removeProperty(key);
        }
    }

    @Override
    public World getWorld() {
        return getParentContext().getWorld();
    }

}
